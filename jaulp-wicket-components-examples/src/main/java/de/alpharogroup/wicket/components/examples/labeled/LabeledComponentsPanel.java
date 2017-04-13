/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.examples.labeled;

import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.core.javascript.JsUtils;

import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Member;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.HomeAddress;
import de.alpharogroup.wicket.components.examples.labeled.address.AddressPanel;
import de.alpharogroup.wicket.components.labeled.checkbox.LabeledCheckboxPanel;
import de.alpharogroup.wicket.components.labeled.label.LabeledEnumLabelPanel;
import de.alpharogroup.wicket.components.labeled.label.LabeledLabelPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledDateTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledDateTimeFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;
import lombok.Getter;

public class LabeledComponentsPanel extends BasePanel<Object>
{
	private static final long serialVersionUID = 1L;

	public static final String COL_SM_1 = "col-sm-1";

	public static final String COL_SM_2 = "col-sm-2";

	public static final String COL_SM_3 = "col-sm-3";

	public static final String COL_SM_4 = "col-sm-4";

	private FeedbackPanel feedbackPanel;
	@Getter
	AddressPanel addressPanel;

	final IModel<HomeAddress> addressModel;

	public LabeledComponentsPanel(final String id)
	{
		super(id);
		setOutputMarkupId(true);
		final Member person = Member.buildMember().build();
		person.setGender(Gender.UNDEFINED);
		person.setName("foo");
		person.setAbout("");
		person.setMarried(false);
		person.setDateofbirth(new Date());
		person.setDateofMarriage(new Date());

		final CompoundPropertyModel<Member> cpm = new CompoundPropertyModel<>(person);

		final Form<Member> form = new Form<>("form", cpm);

		add(form);

		final LabeledEnumLabelPanel<Member> genderLabel = newLabeledEnumLabelPanel("gender", cpm,
			Model.of("Gender:"));
		form.add(genderLabel);

		final LabeledLabelPanel<Member> nameLabel = newLabeledLabelPanel("name", cpm,
			Model.of("Name:"));
		form.add(nameLabel);

		final LabeledTextFieldPanel<String, Member> nameTextField = newLabeledTextFieldPanel(
			"nickname", cpm, Model.of("Input your nickname:"));
		form.add(nameTextField);

		final LabeledTextAreaPanel<String, Member> about = newLabeledTextAreaPanel("about", cpm,
			Model.of("About:"));
		form.add(about);

		final LabeledCheckboxPanel<String, Member> married = newLabeledCheckboxPanel("married", cpm,
			Model.of("Married:"));

		form.add(married);

		final LabeledDateTextFieldPanel<String, Member> dateofbirth = newLabeledDateTextFieldPanel(
			"dateofbirth", cpm, Model.of("Date of birth:"));
		form.add(dateofbirth);


		final LabeledDateTimeFieldPanel<String, Member> dateofMarriage = newLabeledDateTimeFieldPanel(
			"dateofMarriage", cpm, Model.of("Date of marriage:"));
		form.add(dateofMarriage);

		this.addressModel = Model
			.of(HomeAddress.builder().street("").localNumber("").code("").city("").build());

		form.add(this.addressPanel = newAddressPanel("addressPanel", this.addressModel));
		// Create submit button for the form
		final AjaxButton submitButton = new AjaxButton("submitButton", form)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				info("Member:" + person.toString());
				info(":::Address::" + LabeledComponentsPanel.this.addressModel.toString());
				target.add(getAddressPanel());
				target.add(getFeedback());
			}
		};

		form.add(submitButton);


		add(this.feedbackPanel = new FeedbackPanel("feedbackpanel"));
		this.feedbackPanel.setOutputMarkupId(true);
	}

	protected Component getFeedback()
	{
		final PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}

	protected AddressPanel newAddressPanel(final String id, final IModel<HomeAddress> model)
	{
		final AddressPanel addressPanel = new AddressPanel(id, model);
		addressPanel.add(new AttributeAppender("class", ""));

		addressPanel.getStreetNumberPanel().getTwoFormComponent()
			.add(new AttributeAppender("class", " form-group row"));
		addressPanel.getStreetNumberPanel().getTwoFormComponent().getLeftFormComponent()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_3 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control"));

		addressPanel.getStreetNumberPanel().getTwoFormComponent().getRightFormComponent()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_1 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control"));
		addressPanel.getStreetNumberPanel().getLabelComponent()
			.add(new AttributeAppender("class", " control-label " + COL_SM_2));
		addressPanel.getZipcodeCityPanel().getTwoFormComponent()
			.add(new AttributeAppender("class", " form-group row "));
		addressPanel.getZipcodeCityPanel().getTwoFormComponent().getLeftFormComponent()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_1 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control"));
		addressPanel.getZipcodeCityPanel().getTwoFormComponent().getRightFormComponent()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_3 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control"));
		addressPanel.getZipcodeCityPanel().getLabelComponent()
			.add(new AttributeAppender("class", " control-label " + COL_SM_2));
		return addressPanel;
	}

	protected LabeledCheckboxPanel<String, Member> newLabeledCheckboxPanel(final String id,
		final IModel<Member> model, final IModel<String> labelModel)
	{
		final LabeledCheckboxPanel<String, Member> married = new LabeledCheckboxPanel<>(
			id, model, labelModel);
		married.add(new AttributeAppender("class", " form-group"));
		married.getCheckBox()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_4 + "\"></div>")).build()))
			.add(new JqueryStatementsBehavior().add(new BuildableChainableStatement.Builder()
				.label("wrap").args(JsUtils.quotes("<div class=\"checkbox\"></div>")).build()))
			.add(new JqueryStatementsBehavior().add(new BuildableChainableStatement.Builder()
				.label("wrap").args(JsUtils.quotes("<label></label>")).build()))
			.add(new AttributeAppender("class", " checkbox"));
		married.getLabelComponent()
			.add(new AttributeAppender("class", " control-label " + COL_SM_2));
		return married;
	}

	protected LabeledDateTextFieldPanel<String, Member> newLabeledDateTextFieldPanel(
		final String id, final IModel<Member> model, final IModel<String> labelModel)
	{
		final LabeledDateTextFieldPanel<String, Member> dateofbirth = new LabeledDateTextFieldPanel<>(
			id, model, labelModel);
		dateofbirth.add(new AttributeAppender("class", " form-group"));
		dateofbirth.getDateTextField()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_4 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control"));
		dateofbirth.getLabelComponent()
			.add(new AttributeAppender("class", " control-label " + COL_SM_2));
		return dateofbirth;
	}

	protected LabeledDateTimeFieldPanel<String, Member> newLabeledDateTimeFieldPanel(
		final String id, final IModel<Member> model, final IModel<String> labelModel)
	{

		final LabeledDateTimeFieldPanel<String, Member> dateofMarriage = new LabeledDateTimeFieldPanel<String, Member>(
			id, model, labelModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected DateTimeField newDateTimeField(final String id, final IModel<Member> model)
			{
				final PropertyModel<Date> textFieldModel = new PropertyModel<>(
					model.getObject(), getId());
				final DateTimeField dateTextField = new DateTimeField(id, textFieldModel);
				return dateTextField;
			}

		};
		dateofMarriage.add(new AttributeAppender("class", " form-group"));
		dateofMarriage.getDateTimeField()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_4 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control no-border"));
		dateofMarriage.getLabelComponent()
			.add(new AttributeAppender("class", " control-label " + COL_SM_2));
		return dateofMarriage;
	}

	protected LabeledEnumLabelPanel<Member> newLabeledEnumLabelPanel(final String id,
		final IModel<Member> model, final IModel<String> labelModel)
	{
		final LabeledEnumLabelPanel<Member> enumPanel = new LabeledEnumLabelPanel<>(id, model,
			labelModel);
		enumPanel.add(new AttributeAppender("class", " form-group"));
		enumPanel.getEnumLabel()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_4 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control no-border"));
		enumPanel.getLabel().add(new AttributeAppender("class", " control-label " + COL_SM_2));
		return enumPanel;
	}

	protected LabeledLabelPanel<Member> newLabeledLabelPanel(final String id,
		final IModel<Member> model, final IModel<String> labelModel)
	{

		final LabeledLabelPanel<Member> nameLabel = new LabeledLabelPanel<>(id, model,
			labelModel);
		nameLabel.add(new AttributeAppender("class", " form-group"));
		nameLabel.getViewableLabel()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_4 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control no-border"));
		nameLabel.getLabel().add(new AttributeAppender("class", " control-label " + COL_SM_2));
		return nameLabel;
	}

	protected LabeledTextAreaPanel<String, Member> newLabeledTextAreaPanel(final String id,
		final IModel<Member> model, final IModel<String> labelModel)
	{
		final LabeledTextAreaPanel<String, Member> about = new LabeledTextAreaPanel<>(
			id, model, labelModel);
		about.add(new AttributeAppender("class", " form-group"));
		about.getTextArea()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_4 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control"));
		about.getLabelComponent().add(new AttributeAppender("class", " control-label " + COL_SM_2));
		return about;
	}

	protected LabeledTextFieldPanel<String, Member> newLabeledTextFieldPanel(final String id,
		final IModel<Member> model, final IModel<String> labelModel)
	{
		final LabeledTextFieldPanel<String, Member> nameTextField = new LabeledTextFieldPanel<>(
			id, model, labelModel);
		nameTextField.add(new AttributeAppender("class", " form-group"));
		nameTextField.getTextField()
			.add(new JqueryStatementsBehavior()
				.add(new BuildableChainableStatement.Builder().label("wrap")
					.args(JsUtils.quotes("<div class=\"" + COL_SM_4 + "\"></div>")).build()))
			.add(new AttributeAppender("class", " form-control"));
		nameTextField.getLabelComponent()
			.add(new AttributeAppender("class", " control-label " + COL_SM_2));
		return nameTextField;
	}
}
