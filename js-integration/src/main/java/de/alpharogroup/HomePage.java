package de.alpharogroup;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import de.alpharogroup.js.behaviors.simple.alert.WicketAlertJsBehavior;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		Label version = new Label("version", getApplication().getFrameworkSettings().getVersion());
		version.add(new WicketAlertJsBehavior());
		add(version);
    }
}
