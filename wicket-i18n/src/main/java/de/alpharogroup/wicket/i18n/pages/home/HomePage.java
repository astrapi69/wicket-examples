package de.alpharogroup.wicket.i18n.pages.home;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.i18n.components.panels.dynval.DynamicValuesPanel;
import de.alpharogroup.wicket.i18n.components.panels.propbased.PropertyBasedPanel;
import de.alpharogroup.wicket.i18n.model.MessagePreferences;
import de.alpharogroup.wicket.i18n.model.Summary;

public class HomePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters)
	{
		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		add(new DynamicValuesPanel("dynamicValuesPanel", Model.<Summary> of(Summary.builder()
			.otherCount("5").title("Wicket in Action").rate("excellent").build())));

		add(new PropertyBasedPanel("propertyBasedPanelLong", Model.<Summary> of(Summary.builder()
			.otherCount("5").title("Wicket in Action").rate("excellent").build())));

		add(new PropertyBasedPanel("propertyBasedPanelShort",
			Model.<Summary> of(Summary.builder().otherCount("5").title("Wicket in Action")
				.rate("excellent").messagePreferences(MessagePreferences.SHORT).build())));

	}
}

