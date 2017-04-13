package de.alpharogroup.swap.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.swap.SwapFragmentPanel;

public abstract class SwapComponentsFragmentPanel<T> extends SwapFragmentPanel<T> {

	private static final long serialVersionUID = 1L;
	
	public SwapComponentsFragmentPanel(String id, IModel<T> model) {
		super(id, model);
		setDefaultModel(new CompoundPropertyModel<T>(model));
		setOutputMarkupPlaceholderTag(true);
		add(view = newFragmentView(FRAGMENT_ID));
		edit = newFragmentEdit(FRAGMENT_ID);
	}

	/**
	 * Creates the fragment to view person.
	 *
	 * @return the fragment
	 */
	protected Fragment newFragmentView(final String id) {
		Fragment viewFragment = new Fragment(id, "view", this, getModel());
		viewFragment.setOutputMarkupPlaceholderTag(true);
		viewFragment.add(newViewComponent("viewPanel", getModel()));
		return viewFragment;
	}
	
	protected abstract Component newViewComponent(String id, IModel<T> model);

	/**
	 * Creates the fragment to edit person.
	 *
	 * @return the fragment
	 */
	protected Fragment newFragmentEdit(final String id) {
		Fragment editFragment = new Fragment(id, "edit", this,
				getDefaultModel());
		editFragment.setOutputMarkupPlaceholderTag(true);
		editFragment.add(newEditComponent("editPanel", getModel()));		
		return editFragment;
	}
	
	protected abstract Component newEditComponent(String id, IModel<T> model);

}
