/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.server.gwt.rpc.impl;

import id.co.sigma.common.annotation.DoubleSubmitSecuredMethod;
import id.co.sigma.common.data.CommonLibraryConstant;
import id.co.sigma.common.data.ObjectSerializer;
import id.co.sigma.common.data.app.DualControlEnabledOperation;
import id.co.sigma.common.data.serializer.JSONSerializerManager;
import id.co.sigma.common.data.serializer.json.ListDataWrapperJSONFriendlyType;
import id.co.sigma.common.data.serializer.json.ListDataWrapperPrimitiveType;
import id.co.sigma.common.data.serializer.json.NonPrimitiveArrayDataWrapper;
import id.co.sigma.common.data.serializer.json.ObjectSerializerManager;
import id.co.sigma.common.data.serializer.json.PrimitiveArrayDataWrapper;
import id.co.sigma.common.data.serializer.json.RPCResponseWrapper;
import id.co.sigma.common.data.serializer.json.SimpleObjectWrapper;
import id.co.sigma.common.exception.InvalidTokenException;
import id.co.sigma.common.exception.LoginExpiredException;
import id.co.sigma.common.exception.SimpleJSONSerializableException;
import id.co.sigma.common.exception.TokenNotPassedException;
import id.co.sigma.common.server.dao.util.ServerSideParsedJSONContainer;
import id.co.sigma.common.server.gwt.rpc.IRPCHandler;
import id.co.sigma.common.server.gwt.rpc.IRPCHandlerManager;
import id.co.sigma.common.server.gwt.rpc.IRPCHandlerMethodInvoker;
import id.co.sigma.common.server.service.IRPCSecurityService;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.JSONCharacterEscapeUtil;
import id.co.sigma.common.util.json.ParsedJSONContainer;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.context.request.RequestContextHolder;

/**
 *
 * @author gps
 */
public class RPCHandlerManagerImpl implements  IRPCHandlerManager , ApplicationContextAware{

    
     
   
    
    static final ArrayList<String> PRIMITIVE_FQCN = new ArrayList<String>(); 
   
    
    
    /**
     * akses ke session registry
     */
    private SessionRegistry sessionRegistry ; 
    
    Map<String, IRPCHandlerMethodInvoker> invokerMap = new HashMap<String, IRPCHandlerMethodInvoker>(); 
    
    
    private static final Logger logger = LoggerFactory.getLogger(RPCHandlerManagerImpl.class);
    
    
    @Autowired
    private IRPCSecurityService rpcSecurityService; 
    @Override
    public void registerHandler(final IRPCHandler handler) {
        Class<?> interfaceImplemented =  handler.implementedInterface(); 
        final String namaIface =  handler.implementedInterface().getName(); 
        Method[] methods =  interfaceImplemented.getMethods() ; 
        Logger logger =  LoggerFactory.getLogger(RPCHandlerManagerImpl.class);
        String message = ">>> " + handler.getClass().getName() + " reporting for duty";  
        logger.debug(message) ;
        System.out.println(message);
        if ( methods != null && methods.length>0){
            for (final  Method scn : methods ){
               String keyBase = namaIface +"." + scn.getName();
               StringBuilder key = new StringBuilder( keyBase) ;
               final Class<?>[] paramTypes =  scn.getParameterTypes(); 
               if ( paramTypes!= null && paramTypes.length>0){
                   for (Class<?> prm : paramTypes ){
                       key.append(prm.getName());
                   }
               }
               
               IRPCHandlerMethodInvoker methodInvoker = instantiateHandler(handler,    scn ,  paramTypes ); 
               
               invokerMap.put(key.toString() , methodInvoker );
            }
        }
    }

    
    
    
    /**
     * generate method handler RPC. wrap method
     * @param handler instance handler yang akan menangani RPC
     * @param methodToInvoke method yang din invoke 
     */
    private IRPCHandlerMethodInvoker instantiateHandler (final IRPCHandler<?> handler ,final Method methodToInvoke , final Class<?>[] paramTypes ) {
    	boolean doubleSubmitSecured =  methodToInvoke.isAnnotationPresent(DoubleSubmitSecuredMethod.class);
    	
    	if ( doubleSubmitSecured){
    		StringBuffer paramsBuff = new StringBuffer(); 
    		for ( Class<?> p : paramTypes ){
    			paramsBuff.append(  p.getName() ) ;
    			paramsBuff.append(";"); 
    		}
    		if ( paramsBuff.length()> 0 ){
    			paramsBuff.deleteCharAt(paramsBuff.length()-1); 
    		}
    		final String methodName = handler.getClass().getName() + "." +  methodToInvoke.getName() +"(" + paramsBuff.toString() + ")" ;
    		
    		
    		return new IRPCHandlerMethodInvoker() {
    			@Override
    			public Object invokeMethod(HttpServletRequest request,
    					Object[] serialzedArguments) throws Exception {
    				String token =  request.getParameter(CommonLibraryConstant.DOUBLE_SUBMIT_AVOIDENCE_PARAMTER_KEY); 
    				if ( token== null || token.isEmpty()){
    					 TokenNotPassedException nOex = new TokenNotPassedException("Mohon di review pemanggilan rpc:["  + methodName +"],\nanda wajib menyediakan token pengaman double submit, dan token ini tidak di temukan dalam parameter. sebagai catatan token di sertakan dalam parameter request : " + CommonLibraryConstant.DOUBLE_SUBMIT_AVOIDENCE_PARAMTER_KEY , methodName); 
    					throw nOex;  
    				}
    				try {
    					rpcSecurityService.checkAndExpiredToken(token);
					} catch (InvalidTokenException e) {
						InvalidTokenException exc = new InvalidTokenException("Method : [" + methodName + "] di eksekusi dengan mempergunakan token yang sudah di pergunakan sebelumnya, atau tidak mengirimkan token. Kemungkinan data tersubmit 2 kali. anda perlu mengecek masalah ini", token);
						throw exc ; 
					}
    				
    				return actualInvokeWorker(request, serialzedArguments, paramTypes, handler, methodToInvoke);
    			}
    		}; 
    	}else{
    		return new IRPCHandlerMethodInvoker() {
                @Override
                public Object invokeMethod(HttpServletRequest request,   Object[] serialzedArguments) throws Exception {
                	 return actualInvokeWorker(request, serialzedArguments, paramTypes, handler, methodToInvoke);
                     
                }            
             }; 
    	}
    	
    	
    }
    
    
    
    /**
     * karena di share, maka invoker di pisah dalam method yang berbeda. jadinya versi yang di 
     */
    private Object actualInvokeWorker (HttpServletRequest request,   Object[] serialzedArguments, final Class<?>[] paramTypes, final IRPCHandler<?> handler ,final Method methodToInvoke) throws Exception{
    	
    	try {
			Method m = BeanUtils.findDeclaredMethod(handler.getClass(),
					methodToInvoke.getName(), paramTypes);
			if (m == null) {
				Logger log = LoggerFactory
						.getLogger(RPCHandlerManagerImpl.class);
				StringBuffer buff = new StringBuffer();
				buff.append("method : " + handler.getClass() + "#"
						+ methodToInvoke.getName());
				buff.append("param signature : ");
				for (Class<?> cls : paramTypes) {
					buff.append(cls.getName());
				}
				log.error(buff.toString());
			}
			return m.invoke(handler, serialzedArguments);
		} catch (Exception ex) {
			logger.error("gagal invoke method : "
					+ methodToInvoke.getName() + ", error message : "
					+ ex.getMessage());
			throw ex;
		}
    }
    
    @Override
    public RPCResponseWrapper invokeRPCRequest(HttpServletRequest request ) throws LoginExpiredException ,  Exception {
    	Object swap =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if ( swap == null|| swap instanceof String || swap instanceof org.springframework.security.authentication.AnonymousAuthenticationToken){// kalau kosong mencurigakan dia expired
    		throw new LoginExpiredException(); 
    	}
    	
    	if ( sessionRegistry!= null) {
    		SessionInformation inf =  sessionRegistry.getSessionInformation(RequestContextHolder.currentRequestAttributes().getSessionId()); 
    		if ( inf== null){
    			throw new LoginExpiredException(); 
    		}
    	}
    	
    	
        String methodIndexer = request.getParameter("METHOD_TO_INVOKE"); 
        String paramTypeFQCNArray = request.getParameter("PARAM_FQCN");
        Object [] params = null ; 
        if ( paramTypeFQCNArray!= null &&  paramTypeFQCNArray.length()>0){
            String[] fqcnSplited = paramTypeFQCNArray.split("\\-");
            params = new Object[fqcnSplited.length];
            for ( int i = 0 ; i < fqcnSplited.length;i++){
                String paramFQCN = fqcnSplited[i]  ; 
                String rawArg = request.getParameter("RPC_PARAM_" + i);
                rawArg = JSONCharacterEscapeUtil.getInstance().convertToUrlDecodedString(rawArg);
                params[i ] = deserializer(rawArg, paramFQCN); 
                methodIndexer+=paramFQCN;
            }
        }
        
        
        
        
        RPCResponseWrapper w = null ; 
        try{
        	
        	IRPCHandlerMethodInvoker invoker  =  invokerMap.get(methodIndexer); 
        	if ( invoker== null){
        		logger.error("tidak ada handler untuk interface : " +methodIndexer + "param : " + paramTypeFQCNArray +",anda perlu mengecek definisi async anda");
        		throw new Exception("Method : " + methodIndexer + ", parameter : " +paramTypeFQCNArray +" silakan laporkan ini ke developer anda. method ini belum di siapkan" ) ; 
        	}
            Object result = invoker.invokeMethod(request,  params);
       
            w = new RPCResponseWrapper(JSONSerializerManager.getInstance().transalateToFriendlyObject(result));
            
        }catch ( Exception exc){
        	Exception actualExc = exc ; 
            if (exc instanceof InvocationTargetException){
            	// kalau java.lang.reflect.InvocationTargetException exception di bungkus, di unpact saja. cari pattern lain
            	Throwable  th  = ((InvocationTargetException)exc).getTargetException() ;
            	if ( th!= null && th instanceof Exception )
            		actualExc = (Exception)th ;
            }else{
            	
            }
        	
        	
            w = new RPCResponseWrapper( tanslateException(actualExc)); 
            logger.error("gagal invoke RPC : " + methodIndexer + ", error di laporkan : " + exc.getMessage() , exc );
        }
        return w ;
    }
    
    
    /**
     * merubah jadi serializable exception
     */
    protected SimpleJSONSerializableException tanslateException (Exception exception ){
        return new SimpleJSONSerializableException(exception);
    }
    
    
    
    
    
    /**
     * deserialize dari raw string data menjadi object
     **/
    protected Object deserializer (String rawData , String classFQCN    ) {
        if ( rawData == null || rawData.isEmpty())
            return null ; 
        if ( ObjectSerializerManager.isSimpleObject(classFQCN)){
    		
        	SimpleObjectWrapper w = new SimpleObjectWrapper(); 
        	 try {
        		 ParsedJSONContainer parser = SharedServerClientLogicManager.getInstance().getJSONParser().parseJSON(rawData);
				SimpleObjectWrapper smpWrapper =  w.instantiateFromJSON(parser) ; 
				return smpWrapper.getActualData();
			} catch (Exception e) {
				
				e.printStackTrace();
				return null; 
			}
        	
        	
        	
    	}
        if ( ObjectSerializerManager.getInstance().isHaveSerializer(classFQCN))
            return  ObjectSerializerManager.getInstance().getSerializer(classFQCN).deserialize(rawData); 
        
        try{
        	
        	
        	
        	
        	if ( java.util.List.class.isAssignableFrom(Class.forName(classFQCN))){
        		return javaUtilListSubClassSerializer(rawData);
        	}
        	else if ( classFQCN.startsWith("[L")){
        		ObjectSerializer<?> s = arrayOfObjectSerializer(classFQCN); 
        		if ( s!= null){
        			ObjectSerializerManager.getInstance().registerSerializer(s);
        			return s.deserialize(rawData);
        		} 
        	}else{
        		return simpleObjectSerializer(rawData , classFQCN);
        	}
          
        }catch ( Exception exc){
        	LoggerFactory.getLogger(RPCHandlerManagerImpl.class).error("gagal mengkonversi class : " + classFQCN  + ", error message : " + exc.getMessage() ,exc) ;
            return null; 
        }
        return null ;
         
    }
    
    
    
    
    
    
    
    
    /**
     * construct list type
     **/
    protected Object javaUtilListSubClassSerializer (String rawData) {
    	ServerSideParsedJSONContainer p = new ServerSideParsedJSONContainer(rawData);
		String actualFQCN = p.getAsString(IJSONFriendlyObject.FQCN_MARKER_KEY);
		if (ObjectSerializerManager.isSimpleObject(actualFQCN)){
			ListDataWrapperPrimitiveType l = new ListDataWrapperPrimitiveType();
			
			ListDataWrapperPrimitiveType act =  l.instantiateFromJSON(p);
			return act.getActualData(); 
		}else{
			ListDataWrapperJSONFriendlyType l = new ListDataWrapperJSONFriendlyType(); 
			ListDataWrapperJSONFriendlyType act =  l.instantiateFromJSON(p);
			return act.getActualData();
		}
    	
    }
    
    
   
    
    
    
    
    
    /**
     * serializer object array
     **/
    private ObjectSerializer<?> arrayOfObjectSerializer ( final  String classFQCN) {
    	String fqcnNoArrayMarker = classFQCN.substring(2 , classFQCN.length()-1) ;
    	
    	if (ObjectSerializerManager.isSimpleObject(fqcnNoArrayMarker)){
    		
    		final ObjectSerializer<Object[]> s = new ObjectSerializer<Object[]>() {
    			
    			private final String[] CLS ={classFQCN};
    			@Override
    			public String[] acceptedClassFQCNS() {
    				return CLS;
    			}
    			@Override
    			public Object[] deserialize(String stringRepresentation) {
    				final ServerSideParsedJSONContainer c = new ServerSideParsedJSONContainer(stringRepresentation);
    	    		final PrimitiveArrayDataWrapper p = new PrimitiveArrayDataWrapper(c ); 
    	    		return p.getActualData();
    			}
				@Override
				public String serialize(Object[] object) {
					final ServerSideParsedJSONContainer c = new ServerSideParsedJSONContainer( );
    	    		final PrimitiveArrayDataWrapper p = new PrimitiveArrayDataWrapper( object );
    	    		p.translateToJSON(c);
					return c.getJSONString();
				}
			};
			
    		return s  ; 
    		
    	}else{
    		final ObjectSerializer<IJSONFriendlyObject<?>[]> s = new ObjectSerializer<IJSONFriendlyObject<?>[]>() {

    			
    			private final String[] CLS ={classFQCN} ; 
				@Override
				public String serialize(IJSONFriendlyObject<?>[] object) {
		    		NonPrimitiveArrayDataWrapper<?> sample = new NonPrimitiveArrayDataWrapper(object); 
		    		ServerSideParsedJSONContainer c = new ServerSideParsedJSONContainer(); 
		    		sample.translateToJSON(c); 
		    		return c.getJSONString(); 
				}

				@Override
				public IJSONFriendlyObject<?>[] deserialize(String stringRepresentation) {
					ServerSideParsedJSONContainer c = new ServerSideParsedJSONContainer(stringRepresentation);
		    		NonPrimitiveArrayDataWrapper<?> sample = new NonPrimitiveArrayDataWrapper(); 
		    		return sample.instantiateFromJSON(c).getActualData();
				}

				@Override
				public String[] acceptedClassFQCNS() {
					return CLS;
				}
    			
    			
    			
    		};
    		
    		return s ;
    		
    	}
    	
    }
    
    
    
    public static void main (String[] arg){
    	
    	
    	/*
    	String fqcn ="L[java.lang.String;";
    	System.out.println(fqcn.substring(2 , fqcn.length()-1));
    	//DualControlEnabledOperation.class.getEnumConstants()
    	String json ="{\"internalCode\":\"insert\"}";
    	ServerSideParsedJSONContainer p = new ServerSideParsedJSONContainer(json);
    	DualControlEnabledOperation rslt =  DualControlEnabledOperation.DELETE.instantiateFromJSON(p);
    	
    	
    	Object swap;
		try {
			swap = Class.forName( DualControlEnabledOperation.class.getName()).getEnumConstants()[0];
			if ( swap instanceof IJSONFriendlyObject){
	    		System.out.println("hore, yes , yes");
	    	}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
    	
    	System.err.println(rslt.toString());
    	*/
    	Method[] ms =  RPCHandlerManagerImpl.class.getDeclaredMethods();
    	for ( Method m  : ms ) {
    		System.out.println(  m.getName());
    		
    	}
    	
    	
    }
    
    
    
    
    
    
    /**
     * serializer simple object
     **/
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Object simpleObjectSerializer (String rawData , String classFQCN) throws Exception{
    	
    	
    	
		Class cls = Class.forName(classFQCN) ;
    	Object tmp = null  ; 
    	if ( cls.isEnum()){
    		Object allEnumVals[] =  cls.getEnumConstants();
    		if ( allEnumVals== null)
    			return null ;
    		tmp = allEnumVals[0];// ambil saja index 1 karena kita cuma perlu sample, bukan real value
    	}
    	else{
    		tmp=  BeanUtils.instantiate(  cls);
    	}
    	
    	final Object sample  = tmp; 
    	
          
         if ( sample instanceof IJSONFriendlyObject){
              final String[] FQCNS ={
                  sample.getClass().getName()
              };  
              final IJSONFriendlyObject convertedObj = (IJSONFriendlyObject)sample ; 
              ObjectSerializerManager.getInstance().registerSerializer(new ObjectSerializer<IJSONFriendlyObject>(){

                  @Override
                  public String[] acceptedClassFQCNS() {
                      return FQCNS ; 
                  }
                  @Override
                  public IJSONFriendlyObject deserialize(String stringRepresentation) {
                      return (IJSONFriendlyObject) convertedObj.instantiateFromJSON(new ServerSideParsedJSONContainer(stringRepresentation)); 
                  }
                  @Override
                  public String serialize(IJSONFriendlyObject object) {
                      ServerSideParsedJSONContainer c = new ServerSideParsedJSONContainer(); 
                      object.translateToJSON(c);
                      return c.getJSONString(); 
                  }
              });
              return  ObjectSerializerManager.getInstance().getSerializer(classFQCN).deserialize(rawData); 
          }
          return null ; 
    }

	@Override
	public void setApplicationContext(ApplicationContext appCtx)
			throws BeansException {
		try {
			Object swap =  appCtx.getBean(SessionRegistry.class);
			if ( swap != null){
				sessionRegistry = (SessionRegistry)swap; 
			}
		} catch (Exception e) {
			logger.error("gagal membaca session registry. error : " + e.getMessage() , e);
		}
		
	}
   
    
}
