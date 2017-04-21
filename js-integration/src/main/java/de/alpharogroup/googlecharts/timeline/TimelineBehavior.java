package de.alpharogroup.googlecharts.timeline;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

public class TimelineBehavior extends Behavior
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private final TextTemplate timelineTemplate = new PackageTextTemplate(TimelineBehavior.class,
		"timeline.js.tmpl");

	private Component component;

	@Override
	public void bind(final Component component)
	{
		super.bind(component);
		this.component = component;
		this.component.setOutputMarkupId(true);
	}

	protected String generateJS(final TextTemplate textTemplate)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("chart_id", "chart_id");
		textTemplate.interpolate(variables);
		String js = textTemplate.asString();
		return js;
	}

	/**
	 * Replace the created map with the variables in the method
	 * {@link TimelineBehavior#generateJS(TextTemplate)} with in the js-template
	 * 'timeline-template.js'.
	 */
	@Override
	public void renderHead(Component c, final IHeaderResponse response)
	{
		// response.render(JavaScriptHeaderItem.forReference(new TimelineResourceReference()));
		response.render(JavaScriptHeaderItem
			.forReference(new UrlResourceReference(Url.parse("https://www.google.com/jsapi"))));
		response.render(OnLoadHeaderItem.forScript(generateJS(timelineTemplate)));
	}

}
