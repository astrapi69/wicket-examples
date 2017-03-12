package de.alpharogroup.wicket.components.examples.fragment.swapping.viewedit;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.test.objects.Person;
import de.alpharogroup.wicket.components.editable.checkbox.EditableCheckbox;
import de.alpharogroup.wicket.components.editable.textarea.EditableTextArea;
import de.alpharogroup.wicket.components.editable.textfield.EditableTextField;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

public class ViewOrEditExamplePanel extends GenericPanel<Person>
{

	private static final long serialVersionUID = 1L;
	private boolean enableFields = true;
	private final EditableTextArea<Person> about;
	private final EditableTextField<Person> nameTextField;
	private final EditableCheckbox<Person> married;
	private final Form<Person> form;
	private final AjaxButton submitButton;

	public ViewOrEditExamplePanel(final String id, final IModel<Person> model)
	{
		super(id, model);

		addOrReplace(form = newForm("form", model));
		nameTextField = EditableTextField.of("name", model, Model.of("Name"));
		form.add(nameTextField);

		about = EditableTextArea.of("about", model, Model.of("About"));
		form.add(about);

		married = EditableCheckbox.of("married", model, Model.of("Married"));

		form.add(married);

		// Create submit button for the form
		form.addOrReplace(submitButton = newAjaxButton("submitButton", form));
	}


	/**
	 * Factory method for create the new {@link Form}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Form}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Form}
	 */
	protected Form<Person> newForm(final String id, final IModel<Person> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	public AjaxButton newAjaxButton(final String id, final Form<Person> form) {
		final AjaxButton submitButton = new AjaxButton(id, form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				ViewOrEditExamplePanel.this.onSubmit(target, form);
			}
		};
		return submitButton;
	}

	public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
	{
		info("Person:" + getDefaultModelObjectAsString());
		ViewOrEditExamplePanel.this.enableFields = !ViewOrEditExamplePanel.this.enableFields;
		ViewOrEditExamplePanel.this.setModelObject(ViewOrEditExamplePanel.this.getModelObject());
		if (ViewOrEditExamplePanel.this.enableFields)
		{
			about.getSwapPanel().onSwapToEdit(target, form);
			nameTextField.getSwapPanel().onSwapToEdit(target, form);
			married.getSwapPanel().onSwapToEdit(target, form);
		}
		else
		{
			about.getSwapPanel().onSwapToView(target, form);
			nameTextField.getSwapPanel().onSwapToView(target, form);
			married.getSwapPanel().onSwapToView(target, form);
		}
	}
}
