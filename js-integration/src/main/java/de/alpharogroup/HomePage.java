package de.alpharogroup;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.js.behaviors.simple.alert.WicketAlertJsBehavior;
import de.alpharogroup.popupoverlay.PopupoverlayBehavior;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		
		Label version = new Label("version", getApplication().getFrameworkSettings().getVersion());
		version.setOutputMarkupId(true);
		version.add(new WicketAlertJsBehavior());
		
		WebMarkupContainer overlayReference = new WebMarkupContainer("overlayReference");
		overlayReference.setOutputMarkupId(true);
		add(overlayReference);
		overlayReference.add(new PopupoverlayBehavior(overlayReference));
		Button openButton = new Button("openButton");
		openButton.add(new AttributeModifier("class", overlayReference.getMarkupId()+ "_open"));
		add(openButton);
		
		Button button = new Button("button");
		button.add(new AttributeModifier("class", overlayReference.getMarkupId()+ "_close"));
		overlayReference.add(button);       
		add(version);
    }
}
