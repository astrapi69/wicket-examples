package de.alpharogroup.swap;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class PersonPanel extends SwapFragmentPanel<PersonModel>
{

	private static final long serialVersionUID = 1L;

	public PersonPanel(String id, IModel<PersonModel> model)
	{
		super(id, model);
		setDefaultModel(new CompoundPropertyModel<PersonModel>(model));
		setOutputMarkupPlaceholderTag(true);
		add(view = newFragmentView(FRAGMENT_ID));
		edit = newFragmentEdit(FRAGMENT_ID);
	}

	/**
	 * Creates the fragment to edit person.
	 *
	 * @return the fragment
	 */
	protected Fragment newFragmentEdit(final String id)
	{
		Fragment editFragment = new Fragment(id, "edit", this, getDefaultModel());
		editFragment.setOutputMarkupPlaceholderTag(true);
		Form<PersonModel> form = new Form<PersonModel>("editPersonForm");
		form.add(new TextField<String>("firstName"));
		form.add(new TextField<String>("lastName"));
		form.add(new TextField<String>("gender"));
		form.add(new TextField<String>("age"));
		form.add(new AjaxFallbackButton("submit", form)
		{
			private static final long serialVersionUID = 1L;

			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(edit);
				swapFragments();
			}
		});
		editFragment.add(form);
		return editFragment;
	}

	/**
	 * Creates the fragment to view person.
	 *
	 * @return the fragment
	 */
	protected Fragment newFragmentView(final String id)
	{
		Fragment viewFragment = new Fragment(id, "view", this, getDefaultModel());
		viewFragment.add(new AjaxFallbackLink<Object>("editLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				swapFragments();
				target.add(view);
			}
		});
		viewFragment.add(new Label("firstName"));
		viewFragment.add(new Label("lastName"));
		viewFragment.add(new Label("gender"));
		viewFragment.add(new Label("age"));
		viewFragment.setOutputMarkupPlaceholderTag(true);
		return viewFragment;
	}

}