package de.alpharogroup.googlecharts.timeline;

import org.apache.wicket.markup.html.panel.Panel;

public class TimelinePanel extends Panel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public TimelinePanel(String id)
	{
		super(id);
		setOutputMarkupId(true);
	}

	// @Override
	// public void renderHead(IHeaderResponse response)
	// {
	// super.renderHead(response);
	// response.render(JavaScriptHeaderItem.forReference(new TimelineResourceReference()));
	// }

}
