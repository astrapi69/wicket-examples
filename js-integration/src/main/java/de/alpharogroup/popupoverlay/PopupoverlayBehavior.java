package de.alpharogroup.popupoverlay;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

import de.alpharogroup.js.behaviors.simple.alert.WicketAlertJsBehavior;

public class PopupoverlayBehavior  extends Behavior {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	Component component;
	public PopupoverlayBehavior(Component component) {
		super();
		this.component = component;
	}

	public static final ResourceReference pluginReference = new JavaScriptResourceReference(PopupoverlayBehavior.class,
            "jquery.popupoverlay.js");
	

	private final TextTemplate wicketAlertTemplate = new PackageTextTemplate(PopupoverlayBehavior.class,
		"popupoverlay-template.js");
	
	protected String generateJS(final TextTemplate textTemplate) {
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("componentId", this.component.getMarkupId());		
		textTemplate.interpolate(variables);
		return textTemplate.asString();
	}

	/** 
	 * Replace the created map with the variables in the method {@link WicketAlertJsBehavior#generateJS(TextTemplate)} with in the js-template 'wicket-alert.js'.
	 */
	@Override
	public void renderHead(Component c, final IHeaderResponse response)	{
		response.render(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings().getJQueryReference()));
		response.render(JavaScriptHeaderItem.forReference(pluginReference));
		response.render(OnLoadHeaderItem.forScript(generateJS(wicketAlertTemplate)));
	}


}
