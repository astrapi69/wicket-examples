package org.wicketstuff.chat.channel.examples.panels.notification;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.header.contributors.HeaderResponseExtensions;

@ImportResources(resources = { @ImportResource(resourceName = "notification.css", resourceType = "css", index = 0) })
public class JqueryNotificationPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public JqueryNotificationPanel(final String id)
	{
		super(id);
		final String fullName = "Asterios Raptis";
		// Space characters must be replaced by character '+'
		final String googleQuery = "http://www.google.com/search?q=" + fullName.replace(" ", "+");
		add(new ExternalLink("externalSite", googleQuery));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		HeaderResponseExtensions.renderHeaderResponse(response, this.getClass());
	}

}
