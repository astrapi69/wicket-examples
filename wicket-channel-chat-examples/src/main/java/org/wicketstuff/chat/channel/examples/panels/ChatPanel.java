package org.wicketstuff.chat.channel.examples.panels;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.examples.application.WicketApplication;
import org.wicketstuff.chat.components.chat.SimpleChatPanel;
import org.wicketstuff.chat.model.ChatroomModel;

public class ChatPanel extends SimpleChatPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChatPanel(final String id, final IModel<ChatroomModel> model)
	{
		super(id, model);
		getChat().add(
			new AttributeAppender("style",
				"border: 1px black solid; width: 500px; height: 300px; overflow-y: auto;"));
	}

	@Override
	protected IChannelService getChannelService()
	{
		return ((WicketApplication)WebApplication.get()).getTimerChannelService();
	}

}
