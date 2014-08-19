/**
 * 
 */
package id.co.sigma.common.client.security.lookup;

import id.co.sigma.common.security.dto.PageDefinitionDTO;
import id.co.sigma.common.client.control.lookup.BaseSimpleSingleResultLookupDialog;
import id.co.sigma.common.client.security.lookup.base.BaseSecurityLookupBrowseText;



/**
 * 
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Aug 15, 2013 2:24:11 PM
 */
public class BrowseLookupPageDefinition extends BaseSecurityLookupBrowseText<Long , PageDefinitionDTO> {

	@Override
	protected BaseSimpleSingleResultLookupDialog<Long, PageDefinitionDTO> instantiateLookup() {
		return new PageDefinitionLookup();
	}

	@Override
	protected String getDataForTextBox(PageDefinitionDTO data) {
		return data.getPageCode();
	}

}
