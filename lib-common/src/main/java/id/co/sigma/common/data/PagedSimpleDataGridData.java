package id.co.sigma.common.data;

import id.co.sigma.common.util.DataIDLocator;
import id.co.sigma.common.util.IDateFormatter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @deprecated pertimbangann untuk tidak mmepergunakan class ini
 **/
@Deprecated
public class PagedSimpleDataGridData extends PagedResultHolder<TransportSimpleGridDataWrapper> {
	
	
	
	/**
	 * pattern yang di pergunakan untuk mengirim data dalam bentuk date as string. ini di pergunakan dalam field {@link TransportSimpleGridDataWrapper#getValues()}
	 **/
	public static final String DATE_TO_STRING_SERIALIZATION_PATTERN = "yyyy-MM-dd HH:mm:ss:S";
	
	
	/**
	 * class class yang di kirim ke client. selain ini di tolak
	 **/
	public static final  String[] TRANSFERED_CLASS = {
		String.class.getName() , 
		Long.class.getName() ,
		"long",
		Integer.class.getName() ,
		"int",
		Double.class.getName() , 
		"double", 
		Float.class.getName() ,
		"float",
		BigInteger.class.getName() ,
		BigDecimal.class.getName() , 
		Boolean.class.getName()  ,
		"boolean", 
		Date.class.getName() , 
		
		
	};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3387756957902227423L;
	/**
	 * indexer, ini akses untuk values
	 **/
	private IndexedDataAccessor indexedDataAccessor ;

	/**
	 * indexer, ini akses untuk values
	 **/
	public IndexedDataAccessor getIndexedDataAccessor() {
		return indexedDataAccessor;
	}

	/**
	 * indexer, ini akses untuk values
	 **/
	public void setIndexedDataAccessor(IndexedDataAccessor indexedDataAccessor) {
		this.indexedDataAccessor = indexedDataAccessor;
	} 
	
	
	/**
	 * mengecek field ini di terima apa ndak dalam proses transfer
	 **/
	public boolean checkIsAcceptedType (Class<?> cls){
		if ( cls==null)
			return false ; 
		for ( String scn : TRANSFERED_CLASS){
			if ( scn.equals(cls.getName()))
				return true ; 
		}
		return false ; 
	}
	
	
	
	/**
	 * tipe class dari data yang di lewatkan
	 **/
	public String[] getClassTypeOfPassedData () {
		if ( indexedDataAccessor!=null)
			return indexedDataAccessor.getIndexerClassNames();
		return null ; 
	}
	
	
	
	/**
	 * menaruh array of data ke dlaam container
	 * @param dataToWrap list of data yang akan di wrap ke dalam paged simple data holder 
	 * @param fieldDataTypes ini adalah data type yang di kirim dalam dataToWrap. kita tidak bisa memeriksa dalam array, karena bisa jadi array ini null
	 * @param beanDefProvider ini bean definition provider. ini bertugas membongkar definisi dari 1 class, field nya apa saja
	 * @param dateFormatter formatter string ke date
	 * 
	 *  
	 **/
	public void pushData ( ArrayList<Class<?>> fieldDataTypes ,  DataIDLocator idLocator,   List<Object[]> dataToWrap , String [] fetchedFields , IDateFormatter dateFormatter)  {
		//ArrayList<Class<?>> actualClasses = beanDefProvider.readPropertyTypeAsArray(objectClass, fetchedFields);
		ArrayList<String> actualAcceptedFields = new ArrayList<String>();
		ArrayList<TransportSimpleGridDataWrapper> swaps =new ArrayList<TransportSimpleGridDataWrapper>(); 
		setHoldedData(swaps); 
		int i = 0 ;
		
		// ini index field yang tidak di sertakan dalam serializasi(karena data type di reject)
		ArrayList<Integer > excludedIndex = new ArrayList<Integer>(); 
		for ( Class<?> scn:fieldDataTypes){
			if ( scn!=null &&checkIsAcceptedType(scn)){
				actualAcceptedFields.add(fetchedFields[i]);
			}else{
				excludedIndex.add(i);
			}
			i++; 
		}
		// singkirkan dari array rejected field class
		for ( int scn : excludedIndex){
			fieldDataTypes.remove(scn);
		}
		
		fetchedFields = new String[actualAcceptedFields.size()];
		actualAcceptedFields.toArray(fetchedFields);
				
		IndexedDataAccessor indexAccessor = new IndexedDataAccessor(); 
		indexAccessor.plugPassedClassesCatalog(fieldDataTypes);
		indexAccessor.setIndexerFields(fetchedFields, excludedIndex);
		setIndexedDataAccessor(indexAccessor);
		 
		if ( dataToWrap!=null){
			for ( Object[]  fieldCandidateToTransfer: dataToWrap ){
				TransportSimpleGridDataWrapper b = new TransportSimpleGridDataWrapper();
				b.setId(idLocator.getDataIDObject(fieldCandidateToTransfer).toString());
				 
				b.setValues(translateObjectArrayToStringArray(0, fieldCandidateToTransfer , dateFormatter, excludedIndex));
				swaps.add(b);
			}
		}
		System.out.println("selesai menyalin data ke PagedSimpleDataGridData, " + excludedIndex.size() + " field di reject >>" + excludedIndex.toArray());
	}
	
	
	/**
	 * menyalin dari rawData  ke dalam object ini
	 * @param rawData data paging yang akan diclone
	 * @param fieldDataTypes field type array, field yang di baca type nya apa. ini untuk proses serialisasi menjadi string
	 * @param objectClass class object underlying data. ini untuk proses reflection
	 *  
	 *  
	 *  
	 **/
	public void absorb (PagedResultHolder<Object[]> rawData ,  ArrayList<Class<?>> fieldDataTypes ,  DataIDLocator idLocator,  String [] fetchedFields , IDateFormatter dateFormatter) {
		setPage(rawData.getPage());
		setPageSize(rawData.getPageSize()); 
		setTotalData(rawData.getTotalData());
		setTotalPage(rawData.getTotalPage()); 
		pushData(fieldDataTypes,idLocator ,  rawData.getHoldedData(),  fetchedFields, dateFormatter);
	}
	/**
	 * worker untuk translate dari object ke string
	 * @param excludedIndex ini index dari dat ayang tidak di kirim ke client
	 **/
	protected String[] translateObjectArrayToStringArray(int startIndex , Object[] objects ,IDateFormatter dateFormatter , ArrayList<Integer > excludedIndex ){
		if ( objects==null||objects.length==0)
			return null ;
		String[] retval = new String[objects.length-startIndex-excludedIndex.size()];
		int targetIndex = 0 ;
		
		for ( int i=startIndex;i<objects.length;i++){
			Object swap = objects[i]; 
			if (excludedIndex.contains(i))
				continue ; 
			if ( swap==null){
				retval[targetIndex++]= null;
				continue ;
			}	
			if ( swap instanceof Date)
				retval[targetIndex]= dateFormatter.format((Date)swap) ; 
			else
				retval[targetIndex]=swap.toString();
			
			targetIndex++;
				
		}
		return retval ; 
	}

}
