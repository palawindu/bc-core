package id.co.sigma.common.client.security.lookup;

import id.co.sigma.common.security.dto.UserDTO;
import id.co.sigma.common.client.control.BaseSimpleSearchComboContentLocator;
import id.co.sigma.common.client.security.lookup.base.SecurityBaseSimpleSingleResultLookupDialog;
import id.co.sigma.common.client.security.rpc.UserRPCServiceAsync;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.StringColumnDefinition;



import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Browse user popup
 * @author I Gede Mahendra
 * @since Dec 10, 2012, 2:47:19 PM
 * @version $Id
 */
public class LookupUser extends SecurityBaseSimpleSingleResultLookupDialog<Long, UserDTO>{

	@Override
	protected String getLookupTitleI18Key() {		
		return "";
	}

	@Override
	protected String getDefaultPopupCaption() {		
		return "Select user";
	}

	@Override
	protected String getGridCaptionI18Key() {		
		return "";
	}

	@Override
	protected String getDefaultGridCaption() {		
		return "User";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected BaseColumnDefinition<UserDTO, ?>[] getLookupGridColumns() {	
		BaseColumnDefinition<UserDTO, ?>[] retval = (BaseColumnDefinition<UserDTO, ?>[]) new BaseColumnDefinition<?,?>[] {
			new StringColumnDefinition<UserDTO>("User Name" , 250 , "") {
				@Override
				public String getData(UserDTO data) {
					return data.getUsername();
				}
			},
			new StringColumnDefinition<UserDTO>("Full Name" , 250 , "") {
				@Override
				public String getData(UserDTO data) {
					return data.getFullName();
				}
			}
		};
		return retval;
	}

	@Override
	protected Class<? extends UserDTO> getLookupDataClass() {	
		return UserDTO.class;
	}

	@Override
	protected void retrieveData(SimpleQueryFilter[] filters, int page,int pageSize, AsyncCallback<PagedResultHolder<UserDTO>> callback) {		
		UserRPCServiceAsync.Util.getInstance().getUserByParameter(filters, page, pageSize, callback);		
	}

	@Override
	protected Integer getDefaultWidth() {		
		return 580;
	}

	@Override
	protected BaseSimpleSearchComboContentLocator[] generateSearchCriteriaLocators() {		
		return new BaseSimpleSearchComboContentLocator[]{
			new BaseSimpleSearchComboContentLocator("userCode", "Username", ""),
			new BaseSimpleSearchComboContentLocator("realName", "Fullname", "")
		};
	}
}
