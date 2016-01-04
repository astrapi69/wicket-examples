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
package de.alpharogroup.wicket.components.examples.sign.up;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.validation.validator.StringValidator;
import org.odlabs.wiquery.core.javascript.JsUtils;

import de.alpharogroup.auth.models.BaseUsernameSignUpModel;
import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;
import de.alpharogroup.wicket.components.examples.basepage.ApplicationBasePanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;
import de.alpharogroup.wicket.components.sign.up.SignupFormPanel;
import de.alpharogroup.wicket.components.sign.up.SignupPanel;
import lombok.Getter;

public class ApplicationSignupPanel extends ApplicationBasePanel<BaseUsernameSignUpModel>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private final int labelSize = 4;
	private final int inputSize = 8;
	@Getter
	private SignupFormPanel signupFormPanel;

	public ApplicationSignupPanel(final String id, final IModel<BaseUsernameSignUpModel> model)
	{
		super(id, Args.notNull(model, "model"));
		add(this.signupFormPanel = newSignupFormPanel("signupFormPanel", model));
	}


	protected SignupFormPanel newSignupFormPanel(final String id, final IModel<BaseUsernameSignUpModel> model) {
		final SignupFormPanel signupFormPanel = new SignupFormPanel("signupFormPanel", model)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Button newButton(final String id)
			{
				final Button button = super.newButton(id);
				button.add(
					new JqueryStatementsBehavior().add(new BuildableChainableStatement.Builder()
						.label("wrap").args(JsUtils.quotes("<div class=\"form-group\"></div>"))
						.build())).add(
					new JqueryStatementsBehavior().add(new BuildableChainableStatement.Builder()
						.label("wrap")
						.args(
							JsUtils.quotes("<div class=\"col-sm-offset-" + labelSize + " col-sm-"
								+ inputSize + "\"></div>")).build()));
				button.add(new AttributeAppender("class", " btn btn-default"));
				return button;
			}

			@Override
			protected Form<?> newForm(final String id,
				final IModel<? extends BaseUsernameSignUpModel> formModel)
			{
				final Form<?> form = super.newForm(id, formModel);
				form.add(new AttributeAppender("class", " form-horizontal col-sm"
					+ (labelSize - inputSize)));
				return form;
			}

			@Override
			protected SignupPanel<BaseUsernameSignUpModel> newSignupPanel(final String id,
				final IModel<BaseUsernameSignUpModel> signupModel)
			{
				final SignupPanel<BaseUsernameSignUpModel> signupPanel =
				 new SignupPanel<BaseUsernameSignUpModel>(id, signupModel)
				{
					/**
					 * The serialVersionUID
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel> newRepeatPasswordTextField(final String id,
						final IModel<BaseUsernameSignUpModel> repeatPasswordModel)
					{
						final LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel> pwTextField = super
							.newRepeatPasswordTextField(id, repeatPasswordModel);
						pwTextField.add(new AttributeAppender("class", " form-group"));
						pwTextField
							.getPasswordTextField()
							.add(
								new JqueryStatementsBehavior()
									.add(new BuildableChainableStatement.Builder()
										.label("wrap")
										.args(
											JsUtils.quotes("<div class=\"col-sm-" + inputSize
												+ "\"></div>")).build()))
							.add(new AttributeAppender("class", " form-control"));
						pwTextField.getLabelComponent().add(
							new AttributeAppender("class", " control-label col-sm-" + labelSize));
						return pwTextField;
					}

					@Override
					protected SigninPanel<BaseUsernameSignUpModel> newSigninPanel(final String id,
						final IModel<BaseUsernameSignUpModel> signinPanelmodel)
					{
						final SigninPanel<BaseUsernameSignUpModel> signinPanel = new SigninPanel<BaseUsernameSignUpModel>(
							id, signinPanelmodel)
						{
							/**
							 * The serialVersionUID
							 */
							private static final long serialVersionUID = 1L;

							@SuppressWarnings("unchecked")
							@Override
							protected Component newEmailTextField(final String id,
								final IModel<BaseUsernameSignUpModel> emailModel)
							{
								final LabeledEmailTextFieldPanel<BaseUsernameSignUpModel> emailTextField = (LabeledEmailTextFieldPanel<BaseUsernameSignUpModel>)super
									.newEmailTextField(id, emailModel);
								emailTextField.add(new AttributeAppender("class", " form-group"));
								emailTextField
									.getEmailTextField()
									.add(new AttributeAppender("type", "email"))
									.add(
										new JqueryStatementsBehavior()
											.add(new BuildableChainableStatement.Builder()
												.label("wrap")
												.args(
													JsUtils.quotes("<div class=\"col-sm-"
														+ inputSize + "\"></div>")).build()))
									.add(new AttributeAppender("class", " form-control"));
								emailTextField.getLabelComponent().add(
									new AttributeAppender("class", " control-label col-sm-"
										+ labelSize));
								return emailTextField;
							}

							@Override
							protected LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel> newPasswordTextField(final String id,
								final IModel<BaseUsernameSignUpModel> passwordModel)
							{
								final LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel> pwTextField = super
									.newPasswordTextField(id, passwordModel);
								pwTextField.add(new AttributeAppender("class", " form-group"));
								pwTextField
									.getPasswordTextField()
									.add(
										new JqueryStatementsBehavior()
											.add(new BuildableChainableStatement.Builder()
												.label("wrap")
												.args(
													JsUtils.quotes("<div class=\"col-sm-"
														+ inputSize + "\"></div>")).build()))
									.add(new AttributeAppender("class", " form-control"));
								pwTextField.getLabelComponent().add(
									new AttributeAppender("class", " control-label col-sm-"
										+ labelSize));
								return pwTextField;
							}
						};
						return signinPanel;
					}

					@SuppressWarnings("unchecked")
					@Override
					protected Component newUsernameTextField(final String id,
						final IModel<BaseUsernameSignUpModel> usernameModel)
					{
						final LabeledTextFieldPanel<BaseUsernameSignUpModel> nameTextField = (LabeledTextFieldPanel<BaseUsernameSignUpModel>)super
							.newUsernameTextField(id, usernameModel);
						nameTextField.add(new AttributeAppender("class", " form-group"));
						nameTextField
							.getTextField()
							.add(StringValidator.lengthBetween(3, 20))
							.add(
								new JqueryStatementsBehavior()
									.add(new BuildableChainableStatement.Builder()
										.label("wrap")
										.args(
											JsUtils.quotes("<div class=\"col-sm-" + inputSize
												+ "\"></div>")).build()))
							.add(new AttributeAppender("class", " form-control"));
						nameTextField.getLabelComponent().add(
							new AttributeAppender("class", " control-label col-sm-" + labelSize));
						nameTextField.getLabelComponent().add(
							new AttributeAppender("class", " control-label col-sm-" + labelSize));
						return nameTextField;
					}
				};
				return signupPanel;
			}

			@Override
			protected void onSignup(final AjaxRequestTarget target, final Form<?> form)
			{
				ApplicationSignupPanel.this.onSignup(target, form);
			}

		};
		return signupFormPanel;
	}

	/**
	 * Callback method that provide the action on sign up.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected void onSignup(final AjaxRequestTarget target, final Form<?> form)
	{
		target.add(getFeedback());
		final String email = getModelObject().getEmail();
		final String username = getModelObject().getUsername();
		final String password = getModelObject().getPassword();
		final String repeatPassword = getModelObject().getRepeatPassword();
		if(password != null && !password.equals(repeatPassword)) {
			error("Password is not same as repeated password");
		}
		if(username != null)
		info("Email: " + email +
			":Username:"	+ username +
			":Password:" + password +
			":RepeatPassword:" + repeatPassword);

	}

}
