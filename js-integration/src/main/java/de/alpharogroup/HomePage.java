package de.alpharogroup;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.googlecharts.timeline.TimelineBehavior;
import de.alpharogroup.googlecharts.timeline.TimelinePanel;
import de.alpharogroup.popupoverlay.PopupoverlayPanel;
import de.alpharogroup.timeline.TimelineJsPanel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

	    // set content of the <title> tag
//		addOrReplace(new Label("jsapi", "").add(new AttributeAppender("src", "https://www.google.com/jsapi")));
		
		Label version = new Label("version", getApplication().getFrameworkSettings().getVersion());
		version.setOutputMarkupId(true);
//		version.add(new WicketAlertJsBehavior());
		add(version);
		
		PopupoverlayPanel popupoverlayPanel = new PopupoverlayPanel("overlayPanel", null);
		add(popupoverlayPanel);
		
		add(new TimelineJsPanel("timelineJsPanel"));
		TimelinePanel emptyPanel = new TimelinePanel("timelinePanel");
		emptyPanel.add(new TimelineBehavior());
		add(emptyPanel);   
		
    }
	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
	}
}
