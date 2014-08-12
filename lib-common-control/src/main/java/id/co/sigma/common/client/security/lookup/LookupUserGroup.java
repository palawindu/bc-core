package id.co.sigma.common.client.security.lookup;

import id.co.sigma.common.security.dto.UserGroupDTO;
import id.co.sigma.common.client.control.BaseSimpleSearchComboContentLocator;
import id.co.sigma.common.client.security.lookup.base.SecurityBaseSimpleSingleResultLookupDialog;
import id.co.sigma.common.client.security.rpc.GroupRPCServiceAsync;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.jquery.client.grid.cols.BaseColumnDefinition;
import id.co.sigma.jquery.client.grid.cols.StringColumnDefinition;

import java.math.BigInteger;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Lookup user group popup
 * @author I Gede Mahendra
 * @since Dec 19, 2012, 4:21:41 PM
 * @version $Id
 */
public class LookupUserGroup extends SecurityBaseSimpleSingleResultLookupDialog<BigInteger, UserGroupDTO>{

	@Override
	protected String getLookupTitleI18Key() {		
		return "";
	}

	@Override
	protected String getDefaultPopupCaption() {		
		return "Select Group";
	}

	@Override
	protected String getGridCaptionI18Key() {		
		return "";
	}

	@Override
	protected String getDefaultGridCaption() {		
		return "User Group";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected BaseColumnDefinition<UserGroupDTO, ?>[] getLookupGridColumns() {
		BaseColumnDefinition<UserGroupDTO, ?>[] retval = (BaseColumnDefinition<UserGroupDTO, ?>[]) new BaseColumnDefinition<?,?>[] {
			new StringColumnDefinition<UserGroupDTO>("Group Code" , 250 , "") {
				@Override
				public String getData(UserGroupDTO data) {
					return data.getGroupCode();
				}
			},
			new StringColumnDefinition<UserGroupDTO>("Group Name" , 250 , "") {
				@Override
				public String getData(UserGroupDTO data) {
					return data.getGroupName();
				}
			}
		};
		return retval;
	}

	@Override
	protected Class<? extends UserGroupDTO> getLookupDataClass() {		
		return UserGroupDTO.class;
	}

	@Override
	protected void retrieveData(SimpleQueryFilter[] filters, int page, int pageSize, AsyncCallback<PagedResultHolder<UserGroupDTO>> callback) {
		//FIXME Dein : SigmaSimpleQueryFilter harus di konvert ke object parameter dlu
		GroupRPCServiceAsync.Util.getInstance().getAllGroup(null, page, pageSize, callback);
	}

	@Override
	protected Integer getDefaultWidth() {		
		return 580;
	}

	@Override
	protected BaseSimpleSearchComboContentLocator[] generateSearchCriteriaLocators() {		
		return new BaseSimpleSearchComboContentLocator[]{
				new BaseSimpleSearchComboContentLocator("groupCode", "Group Code", ""),
				new BaseSimpleSearchComboContentLocator("groupName", "Group Name", "")
		};
	}
}