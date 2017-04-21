package de.alpharogroup.wicket.i18n.components.panels.dynval;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.i18n.model.Summary;

public class DynamicValuesPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	public DynamicValuesPanel(String id, IModel<Summary> model)
	{
		super(id, model);
		IModel<String> resourceModel = ResourceModelFactory.newResourceModel("summ", this, model);
		add(new Label("summary", resourceModel));
	}

}
