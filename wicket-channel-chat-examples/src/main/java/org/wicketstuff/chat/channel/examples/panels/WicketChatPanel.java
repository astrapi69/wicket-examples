package org.wicketstuff.chat.channel.examples.panels;

import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.examples.application.WicketApplication;

public class WicketChatPanel extends WicketAbstractChatPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WicketChatPanel(final String id, final String channel)
	{
		super(id, channel);
	}

	@Override
	protected IChannelService getChannelService()
	{
		return ((WicketApplication)WebApplication.get()).getTimerChannelService();
	}

}
