package id.co.sigma.sandbox.server;

import java.io.Serializable;
import java.util.ArrayList;

import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.sandbox.shared.domain.Person;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class DataPopulator implements InitializingBean{

	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao ;
	
	@Autowired
	private PlatformTransactionManager transactionManager ; 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		TransactionTemplate tmpl = new TransactionTemplate(transactionManager);
		tmpl.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus arg0) {
				try {
					populatePerson();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		});
		
		
		
	}
	
	private void populatePerson () throws Exception {
		ArrayList<Serializable> ps = new ArrayList<Serializable>(); 
		for ( int i = 1 ; i < 200; i++){
			Person p = new Person(); 
			ps.add(p); 
			p.setEmail("sample" + i + "@balicamp.com");
			p.setName("Person - " + i );
		}
		generalPurposeDao.inserts(ps);
	}

}
