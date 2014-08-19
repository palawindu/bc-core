package id.co.sigma.common.client.security.lookup;

import id.co.sigma.common.security.dto.UserDTO;
import id.co.sigma.common.client.control.lookup.BaseSimpleSingleResultLookupDialog;
import id.co.sigma.common.client.security.lookup.base.BaseSecurityLookupBrowseText;



/**
 * Browse button user
 * @author I Gede Mahendra
 * @since Dec 10, 2012, 2:45:57 PM
 * @version $Id
 */
public class BrowseLookupUser extends BaseSecurityLookupBrowseText<Long ,  UserDTO>{

	
	@Override
	protected String getDataForTextBox(UserDTO data) {		
		return data.getUsername();
	}

	@Override
	protected BaseSimpleSingleResultLookupDialog<Long, UserDTO> instantiateLookup() {
		return new LookupUser();
	}
}