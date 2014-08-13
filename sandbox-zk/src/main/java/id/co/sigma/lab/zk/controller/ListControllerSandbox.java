package id.co.sigma.lab.zk.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.sandbox.shared.domain.Person;
import id.co.sigma.zk.ui.QueryParameterEntry;
import id.co.sigma.zk.ui.controller.BaseSimpleListController;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class ListControllerSandbox extends BaseSimpleListController<Person>{

	@QueryParameterEntry(filteredField="email" , queryOperator=SimpleQueryFilterOperator.likeBothSide)
	@Wire
	Textbox emailSearch ; 
	
	@Wire
	Button searchButton ;
	
	@Wire
	Listbox listbox ; 
	
	
	@Override
	protected Class<? extends Person> getHandledClass() {
		return Person.class;
	}
	
	@Listen(value="onClick = #searchButton")
	public void click() {
		invokeSearch();
	};
	
	@Override
	public Listbox getListbox() {
		return listbox;
	}
}
