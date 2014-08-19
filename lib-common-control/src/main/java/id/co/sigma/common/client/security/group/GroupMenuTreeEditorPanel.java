/**
 * 
 */
package id.co.sigma.common.client.security.group;

import id.co.sigma.common.security.domain.ApplicationMenu;
import id.co.sigma.common.security.domain.ApplicationMenuAssignment;
import id.co.sigma.common.security.dto.UserGroupDTO;
import id.co.sigma.common.client.form.ExtendedButton;
import id.co.sigma.common.client.security.BaseAriumSecurityComposite;
import id.co.sigma.common.client.security.control.CheckBoxMenuTree;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Dode
 * @version $Id
 * @since Jan 9, 2013, 3:15:45 PM
 */
public class GroupMenuTreeEditorPanel extends BaseAriumSecurityComposite {

	private static GroupMenuTreeEditorPanelUiBinder uiBinder = GWT
			.create(GroupMenuTreeEditorPanelUiBinder.class);
	@UiField ExtendedButton btnApply;
	@UiField ExtendedButton btnFinish;
	@UiField CheckBoxMenuTree menuTree;
	
	private UserGroupDTO currentData;

	interface GroupMenuTreeEditorPanelUiBinder extends
			UiBinder<Widget, GroupMenuTreeEditorPanel> {
	}

	public GroupMenuTreeEditorPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * get added menu items in function assignment object
	 * @return list of added function assignment
	 */
	public List<ApplicationMenuAssignment> getAddedMenuItems() {
		//ubah dari added list bertipe function ke tipe function assignment agar bisa langsung diinsert
		List<ApplicationMenuAssignment> addedFunctionAssignment = new ArrayList<ApplicationMenuAssignment>();
		if (!menuTree.getAddedMenu().isEmpty()) {
			for (ApplicationMenu addedItem : menuTree.getAddedMenu()) {
				ApplicationMenuAssignment newGroupMenuItem = new ApplicationMenuAssignment();
				newGroupMenuItem.setFunctionId(addedItem.getId());
				newGroupMenuItem.setGroupId(currentData.getId()== null ? null  : currentData.getId().longValue());
				newGroupMenuItem.setCreatedOn(getApplicationDate());
				newGroupMenuItem.setCreatedBy(getCurrentUserLogin());
				addedFunctionAssignment.add(newGroupMenuItem);
			}
		}
		
		return addedFunctionAssignment;
	}
	
	/**
	 * get added menu items in function assignment object
	 * @return list of removed function assignment
	 */
	public List<ApplicationMenuAssignment> getRemoedMenuItems() {
		//ubah dari removed list bertipe function ke tipe function assignment agar lebih mudah untuk deletenya
		List<ApplicationMenuAssignment> removedFunctionAssignment = new ArrayList<ApplicationMenuAssignment>();
		if (!menuTree.getRemovedMenu().isEmpty()) {
			for (ApplicationMenu removedItem : menuTree.getRemovedMenu()) {
				ApplicationMenuAssignment deleteGroupMenuItem = new ApplicationMenuAssignment();
				deleteGroupMenuItem.setFunctionId(removedItem.getId());
				deleteGroupMenuItem.setGroupId(currentData.getId().longValue());
				removedFunctionAssignment.add(deleteGroupMenuItem);
			}
		}
		return removedFunctionAssignment;
	}
	
	/**
	 * set user group current data
	 * @param currentData
	 */
	public void setCurrentData(UserGroupDTO currentData) {
		this.currentData = currentData;
	}
}
