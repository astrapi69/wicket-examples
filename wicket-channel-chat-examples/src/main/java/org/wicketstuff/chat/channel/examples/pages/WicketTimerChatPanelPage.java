package org.wicketstuff.chat.channel.examples.pages;

import java.util.UUID;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.examples.application.WicketApplication;
import org.wicketstuff.chat.channel.examples.panels.ChatPanel;
import org.wicketstuff.chat.channel.examples.panels.WicketChatPanel;
import org.wicketstuff.chat.channel.examples.panels.notification.JqueryNotificationPanel;
import org.wicketstuff.chat.components.notifications.InvitationPanel;
import org.wicketstuff.chat.model.ChatroomModel;

import de.alpharogroup.wicket.header.contributors.HeaderResponseExtensions;
import de.alpharogroup.wicket.js.addon.pnotify.PnotifyJsReference;

public class WicketTimerChatPanelPage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public WicketTimerChatPanelPage(final PageParameters parameters)
	{
		final ChatroomModel model = new ChatroomModel();
		model.setChannel("chatroom3");
		model.setUser(UUID.randomUUID().toString());
		final IModel<ChatroomModel> crModel = Model.of(model);
		add(new JqueryNotificationPanel("jqueryNotificationPanel")
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onConfigure()
			{
				super.onConfigure();
				setVisibilityAllowed(false);
			}
		});
		add(new InvitationPanel("notificationPanel", crModel, Duration.seconds(30))
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected IChannelService getChannelService()
			{
				return ((WicketApplication)WebApplication.get()).getTimerChannelService();
			}

		});
		add(new ChatPanel("chatPanel3", crModel));

		add(new WicketChatPanel("chatPanel", "chatroom1"));
		add(new WicketChatPanel("chatPanel2", "chatroom2"));
	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(PnotifyJsReference.INSTANCE));
		HeaderResponseExtensions.renderHeaderResponse(response, this.getClass());
	}

}
