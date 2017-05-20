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
package de.alpharogroup.wicket.dropdownchoices.pages;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.panels.DoubleDropDownPanel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import de.alpharogroup.wicket.header.contributors.HeaderResponseExtensions;
import de.alpharogroup.wicket.model.dropdownchoices.TwoDropDownChoicesBean;

/**
 * The class {@link DoubleDropDownChoicesPage}.
 *
 * @author Asterios Raptis
 */
@ImportResources(resources = {
		@ImportResource(resourceName = "TwoDropDownChoicesPage.css", resourceType = "css") })
public class DoubleDropDownChoicesPage extends WebPage
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private final TwoDropDownChoicesBean<String> twoDropDownChoicesBean;

	private DoubleDropDownPanel<String> doubleDropDownPanel;

	public DoubleDropDownChoicesPage(final PageParameters pageParameters)
	{
		super(pageParameters);
		this.twoDropDownChoicesBean = new TwoDropDownChoicesBean<>("trademark.audi",
			DatabaseManager.initializeModelMap());
	}

	@Override
	protected void onBeforeRender()
	{
		final AttributeModifier sam = new AttributeModifier("style",
			"width: 200px; margin-bottom: 20px;");
		final AttributeModifier samClass = new AttributeModifier("class", "nowrap");

		final AttributeModifier samSize = new AttributeModifier("size", "3");

		doubleDropDownPanel.getRootChoice().add(sam);
		doubleDropDownPanel.getRootChoice().add(samSize);
		doubleDropDownPanel.getRootChoice().add(samClass);

		doubleDropDownPanel.getChildChoice().add(sam);
		doubleDropDownPanel.getChildChoice().add(new AttributeModifier("size", "4"));
		super.onBeforeRender();
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final IModel<TwoDropDownChoicesBean<String>> boundOptionModel = new PropertyModel<>(this,
			"twoDropDownChoicesBean");

		final Form<TwoDropDownChoicesBean<String>> selectOptionForm = new Form<>("selectOptionForm",
			boundOptionModel);

		add(selectOptionForm);

		doubleDropDownPanel = new DoubleDropDownPanel<>("doubleDropDownPanel", boundOptionModel,
			new PropertiesChoiceRenderer(this, this.getClass()),
			new PropertiesChoiceRenderer(this, this.getClass()));

		selectOptionForm.add(doubleDropDownPanel);

		// Create submit button for the form
		final Button entryButton = new AjaxButton("entryButton", selectOptionForm)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				final String selected = doubleDropDownPanel.getRootChoice().getModelObject();
				super.onSubmit(target, form);
				System.out.println(selected);
			}
		};

		selectOptionForm.add(entryButton);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		HeaderResponseExtensions.renderHeaderResponse(response, this.getClass());
	}

}
