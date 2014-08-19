package id.co.sigma.common.client.security.lookup;

import id.co.sigma.common.security.dto.BranchDTO;
import id.co.sigma.common.client.control.lookup.BaseSimpleSingleResultLookupDialog;
import id.co.sigma.common.client.security.lookup.base.BaseSecurityLookupBrowseText;



/**
 * Browse button branch
 * @author I Gede Mahendra
 * @since Jan 31, 2013, 11:18:03 AM
 * @version $Id
 */
public class BrowseLookupBranch extends BaseSecurityLookupBrowseText<Long, BranchDTO>{

	@Override
	protected BaseSimpleSingleResultLookupDialog<Long, BranchDTO> instantiateLookup() {		
		return new LookupBranch();
	}

	@Override
	protected String getDataForTextBox(BranchDTO data) {		
		return data.getBranchCode();
	}
	
	
}