package de.alpharogroup;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

import com.googlecode.wicket.jquery.core.Options;

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

		Button button = new Button("button");
		button.add(new AttributeModifier("class", overlayReference.getMarkupId()+ "_close"));
		overlayReference.add(button);
		Options options = new Options();        
		version.add(new PopupoverlayBehavior("#"+overlayReference.getMarkupId(), "popup", options));
		add(version);
    }
}
