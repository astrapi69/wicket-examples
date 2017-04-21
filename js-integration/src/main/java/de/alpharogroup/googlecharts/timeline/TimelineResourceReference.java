package de.alpharogroup.googlecharts.timeline;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 * A resource reference that contributes 'charts.js'.
 */
public class TimelineResourceReference extends JavaScriptResourceReference
{
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public TimelineResourceReference()
	{
		super(TimelineResourceReference.class, "charts.js");
	}

	/**
	 * Specify that timeline-template.js depends on Google JS APIs
	 * 
	 * @return a list of dependencies
	 */
	@Override
	public List<HeaderItem> getDependencies()
	{
		List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
		dependencies.add(JavaScriptHeaderItem
			.forReference(new UrlResourceReference(Url.parse("https://www.google.com/jsapi"))));
		return dependencies;
	}
}