package de.alpharogroup.js.behaviors.simple.alert;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

public class WicketAlertJsBehavior  extends Behavior
{
	private static final long serialVersionUID = 1L;

	private final TextTemplate wicketAlertTemplate = new PackageTextTemplate(WicketAlertJsBehavior.class,
		"wicket-alert.js");
	

	
	
	protected String generateJS(final TextTemplate textTemplate) {
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("message", "Hello from wicket behavior.");		
		textTemplate.interpolate(variables);
		return textTemplate.asString();
	}
	private Component component;
	@Override
	public void bind(final Component component)
	{
		super.bind(component);
		this.component = component;
		this.component.setOutputMarkupId(true);
	}

	/** 
	 * Replace the created map with the variables in the method {@link WicketAlertJsBehavior#generateJS(TextTemplate)} with in the js-template 'wicket-alert.js'.
	 */
	@Override
	public void renderHead(Component c, final IHeaderResponse response)	{
		response.render(OnLoadHeaderItem.forScript(generateJS(wicketAlertTemplate)));
	}

}
