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
package de.alpharogroup.wicket.components.examples.labeled.address;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.examples.fragment.swapping.HomeAddress;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.labeled.LabeledTwoFormComponentPanel;
import lombok.Getter;

public class AddressPanel extends BasePanel<HomeAddress>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	LabeledTwoFormComponentPanel<String, String, HomeAddress> zipcodeCityPanel;
	@Getter
	LabeledTwoFormComponentPanel<String, String, HomeAddress> streetNumberPanel;

	public AddressPanel(final String id, final IModel<HomeAddress> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		add(streetNumberPanel = newStreetNumberPanel("streetNumberPanel",
			Model.of("Street / number:")));
		add(zipcodeCityPanel = newZipcodeCityPanel("zipcodeCityPanel", Model.of("Zip / City:")));
	}

	protected LabeledTwoFormComponentPanel<String, String, HomeAddress> newStreetNumberPanel(final String id,
		final IModel<String> labelModel)
	{
		final TwoFormComponentBean<String, String> twoFormComponentBean =
			new TwoFormComponentBean<>(
				new PropertyModel<String>(AddressPanel.this.getModelObject(), "street") ,
				new PropertyModel<String>(AddressPanel.this.getModelObject(), "localNumber"));
		final LabeledTwoFormComponentPanel<String, String, HomeAddress> streetNumberPanel = new LabeledTwoFormComponentPanel<String, String, HomeAddress>(
			id, getModel(), Model.of(twoFormComponentBean), labelModel);
		return streetNumberPanel;
	}

	protected LabeledTwoFormComponentPanel<String, String, HomeAddress> newZipcodeCityPanel(final String id,
		final IModel<String> labelModel)
	{
		final TwoFormComponentBean<String, String> twoFormComponentBean =
			new TwoFormComponentBean<>(
				new PropertyModel<String>(AddressPanel.this.getModelObject(), "code") ,
				new PropertyModel<String>(AddressPanel.this.getModelObject(), "city"));
		final LabeledTwoFormComponentPanel<String, String, HomeAddress> zipcodeCityPanel = new LabeledTwoFormComponentPanel<String, String, HomeAddress>(
			id, getModel(), Model.of(twoFormComponentBean), labelModel);
		return zipcodeCityPanel;
	}

}
