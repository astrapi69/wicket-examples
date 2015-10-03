package org.wicketstuff.chat.channel.examples.panels;

import java.io.Serializable;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.wicketstuff.chat.channel.api.ChannelEvent;
import org.wicketstuff.chat.channel.api.IChannelListener;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.api.IChannelTarget;

/**
 * Examples of chat using {@link IChannelService}.
 * <p>
 * This example is abstract because it doesn't define which channel service implementation it uses.
 * <p>
 * Concrete subclasses only have to provide {@link #getChannelService()} implementation, returning
 * any IChannelService implementation.
 * <p>
 * The whole example doesn't depend on which implementation is used, and show easy it is to switch
 * between implementations.
 * 
 * @author Vincent Demay
 * @author Xavier Hanin
 */
public abstract class WicketAbstractChatPanel extends Panel
{
	private static class Message implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private String chat;
		private String user;
		private String message;

		@SuppressWarnings("unused")
		public String getChat()
		{
			return chat;
		}

		public String getMessage()
		{
			return message;
		}

		public String getUser()
		{
			return user;
		}

		@SuppressWarnings("unused")
		public void setChat(final String chat)
		{
			this.chat = chat;
		}

		@SuppressWarnings("unused")
		public void setMessage(final String message)
		{
			this.message = message;
		}

		@SuppressWarnings("unused")
		public void setUser(final String user)
		{
			this.user = user;
		}

	}

	private static final long serialVersionUID = 1L;
	final String channel;

	@SuppressWarnings("serial")
	public WicketAbstractChatPanel(final String id, final String channel)
	{
		super(id);
		this.channel = channel;
		final Message model = new Message();

		final Form<Message> formChat = new Form<Message>("chatForm",
			new CompoundPropertyModel<Message>(model));

		final TextField<String> field = new TextField<String>("user");
		field.setOutputMarkupId(false);
		formChat.add(field);
		final Label chatroom = new Label("chatroom", channel);
		formChat.add(chatroom);
		final Label chat = new Label("chat");
		chat.setOutputMarkupId(true);
		getChannelService().addChannelListener(this, channel, new IChannelListener()
		{
			@Override
			public void onEvent(final String channel, final Map<String, String> datas,
				final IChannelTarget target)
			{
				target.appendJavaScript("document.getElementById('" + chat.getMarkupId()
					+ "').innerHTML += '<br/>" + datas.get("message") + "'");
			}
		});
		formChat.add(chat);

		final TextField<String> mess = new TextField<String>("message");
		mess.setOutputMarkupId(true);
		formChat.add(mess);

		formChat.add(new AjaxButton("send", formChat)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				// Update message
				final String currentChat = ((Message)form.getModelObject()).getUser() + " said "
					+ ((Message)form.getModelObject()).getMessage();
				// send an event to refesh the chat area
				final ChannelEvent event = new ChannelEvent(channel);
				event.addData("message", currentChat);
				getChannelService().publish(event);

				// clear message area add focus it
				target.appendJavaScript("document.getElementById('" + mess.getMarkupId()
					+ "').value =''");
				target.focusComponent(mess);
			}
		});
		add(formChat);
	}

	protected abstract IChannelService getChannelService();
}
