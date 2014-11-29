package de.alpharogroup;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.js.behaviors.simple.alert.WicketAlertJsBehavior;
import de.alpharogroup.popupoverlay.PopupoverlayBehavior;
import de.alpharogroup.popupoverlay.PopupoverlayPanel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		
		Label version = new Label("version", getApplication().getFrameworkSettings().getVersion());
		version.setOutputMarkupId(true);
		version.add(new WicketAlertJsBehavior());
		add(version);
		
		PopupoverlayPanel popupoverlayPanel = new PopupoverlayPanel("overlayPanel", null);
		add(popupoverlayPanel);
		
     
		
    }
}
