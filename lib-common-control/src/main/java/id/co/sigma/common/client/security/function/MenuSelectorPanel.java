package id.co.sigma.common.client.security.function;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Tree;

import id.co.sigma.common.client.control.ITransformableToReadonlyLabel;
import id.co.sigma.common.client.widget.BaseCommonControlComposite;
import id.co.sigma.common.data.app.security.MenuEditingData;
import id.co.sigma.common.security.domain.ApplicationMenu;
import id.co.sigma.jquery.client.util.JQueryUtils;

/**
 * 
 * Composite untuk editor menu selector. ini berisi tree panel untuk menampung tree menu. jadi apa saja yang bisa di pilih
 * 
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class MenuSelectorPanel extends BaseCommonControlComposite implements ITransformableToReadonlyLabel{
	
	
	private Tree menuTree ; 
	
	
	
	public MenuSelectorPanel() {
		menuTree = new Tree(); 
		initWidget(menuTree);
		
		getElement().setId(DOM.createUniqueId());
	
		
		
	}
	private Map<Long, MenuSelectorTreeItem> indexedTreeItem
			= new HashMap<Long, MenuSelectorTreeItem>(); 
	
	
	/**
	 * render menu data ke dalam panel. render tree + checkmark
	 * @param menuData data menu
	 * @param editable flag editable. kalau true akan ada checkbox pada item
	 */
	public void renderMenu (final MenuEditingData menuData ){
		
		
		menuTree.clear(); 
		
		ArrayList<ApplicationMenu> cloneArray = new ArrayList<ApplicationMenu>() ; 
		cloneArray.addAll(  menuData.getAllMenus()); 
		
		ArrayList<ArrayList<ApplicationMenu>> netstedMenus = new ArrayList<ArrayList<ApplicationMenu>>();
		// 
		
		
		netstedMenus.add(new ArrayList<ApplicationMenu>()); 
		for (ApplicationMenu scn : cloneArray ){
			if ( scn.getFunctionIdParent()== null){
				netstedMenus.get(0).add(scn); 
			}
		}
		for (ApplicationMenu scn : netstedMenus.get(0)){
			cloneArray.remove(scn); 
		}
		int idx = 0 ; 
		
		int maxDeepth = 100000 ; 
		int currentDepth = 0  ; 
		while ( !cloneArray.isEmpty() && currentDepth<maxDeepth){
			netstedMenus.add(new ArrayList<ApplicationMenu>());
			for ( ApplicationMenu scn : cloneArray){
				for ( ApplicationMenu bpk : netstedMenus.get(idx)){
					if (bpk.getId().equals(   scn.getFunctionIdParent()) ){
						netstedMenus.get(idx + 1 ).add(scn); 
						break ; 
					}
				}
				currentDepth++ ; 
				if( currentDepth > maxDeepth)
					break ; 
			}
			if ( netstedMenus.get(idx +1 ).isEmpty()){
				break ; 
			}
			for ( ApplicationMenu scn : netstedMenus.get(idx + 1 )) {
				cloneArray.remove(scn); 
			}
			idx ++ ; 
		}
		
		
		indexedTreeItem.clear(); 
		
		
		for ( ArrayList<ApplicationMenu> levels : netstedMenus) {
			for ( ApplicationMenu scn : levels) {
				MenuSelectorTreeItem itm = new MenuSelectorTreeItem(scn );
				indexedTreeItem.put(scn.getId(), itm); 
				if ( scn.getFunctionIdParent()== null){
					menuTree.addItem( itm);
				}
				else{
					if ( indexedTreeItem.containsKey(scn.getFunctionIdParent())){
						indexedTreeItem.get(scn.getFunctionIdParent()).addItem(itm);
					}
				}
			}
		}
		
		JQueryUtils.getInstance().blockUI(getElement().getId(), "Mohon menunggu");
		new Timer() {
				
			@Override
			public void run() {
				for (MenuSelectorTreeItem scn : indexedTreeItem.values() ){
					boolean enabled =  scn.getChildCount()== 0 ; 
					scn.setEnabled(enabled);
					if ( menuData.getAllSelectedIds()!= null && menuData.getAllSelectedIds().contains(scn.getMenu().getId()))
						scn.setChecked(true);
					if ( readonly)
						scn.switchToReadonlyText();
					
				}
				JQueryUtils.getInstance().unblockUI( getElement().getId()) ; 
			}
		}.schedule(250);
		
		
	}
	
	
	
	/**
	 * capute semua item yang di pilih dalam panel selector menu. jadi child item + parent item
	 */
	public List<Long> getAllSelectedMenuIds () {
		ArrayList<Long> retval = new ArrayList<Long>(); 
		for ( MenuSelectorTreeItem itm : indexedTreeItem.values()){
			if ( itm.isChecked()){
				retval.add(itm.getMenu().getId()); 
			}
		}
		return retval ;
	}



	
	boolean readonly = false ; 
	
	
	@Override
	public void restoreControl() {
		for ( MenuSelectorTreeItem scn : indexedTreeItem.values()){
			scn.restoreControl();
		}
		readonly= false ; 
	}



	@Override
	public void switchToReadonlyText() {
		for ( MenuSelectorTreeItem scn : indexedTreeItem.values()){
			scn.switchToReadonlyText();
		}
		readonly = true ; 
	}
	 
}
