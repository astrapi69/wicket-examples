package de.alpharogroup.timeline;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;


public class TimelineJsPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public TimelineJsPanel(String id)
	{
		this(id, null);
	}

	public TimelineJsPanel(String id, IModel<?> model)
	{
		super(id, model);
		setOutputMarkupId(true);

	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		Url jsapi = Url.parse("http://www.google.com/jsapi");
		UrlResourceReference jsapiReference = new UrlResourceReference(jsapi);
		response.render(JavaScriptHeaderItem.forReference(jsapiReference));
		// js
		response.render(JavaScriptHeaderItem
			.forReference(new JavaScriptResourceReference(TimelineJsPanel.class, "timeline.js")));
		response.render(JavaScriptHeaderItem.forReference(
			new JavaScriptResourceReference(TimelineJsPanel.class, "initTimeline.js")));
		// css
		response.render(CssHeaderItem
			.forReference(new CssResourceReference(TimelineJsPanel.class, "timeline.css")));
	}

}
