package de.alpharogroup.swap.panel;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.alpharogroup.swap.PersonModel;

public class SwapPersonWithPanel extends SwapComponentsFragmentPanel<PersonModel> {

	private static final long serialVersionUID = 1L;
	
	public SwapPersonWithPanel(String id, IModel<PersonModel> model) {
		super(id, model);			
	}
	
	protected Component newViewComponent(String id, IModel<PersonModel> model) {
		return new ViewPersonPanel(id, model) {
			private static final long serialVersionUID = 1L;
			protected void onSubmit(AjaxRequestTarget target) {
				onSwapFromViewToEdit(target, null);
				System.err.println(getModeContext().toString());
			}
		};
	}
	
	protected Component newEditComponent(String id, IModel<PersonModel> model) {
		return new EditPersonPanel(id, model) {
			private static final long serialVersionUID = 1L;
			protected void onSubmit(AjaxRequestTarget target, final Form<?> form) {
				onSwapFromEditToView(target, form);
				System.err.println(getModeContext().toString());
			}
		};
	}

}