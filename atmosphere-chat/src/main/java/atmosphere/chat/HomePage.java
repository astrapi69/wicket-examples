package atmosphere.chat;

import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.atmosphere.Subscribe;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	private Component timeLabel;
	private Component messageLabel;
	private TextField<String> receiver;
	private TextField<String> input;

	public HomePage(final PageParameters parameters)
	{
		super(parameters);

		add(timeLabel = new Label("time", Model.of("start")).setOutputMarkupId(true));
		add(messageLabel = new Label("message", Model.of("-")).setOutputMarkupId(true));

		Form<Void> form = new Form<Void>("form");
		add(form);
		form.add(receiver = new TextField<String>("receiver", Model.of("")));
		form.add(input = new TextField<String>("input", Model.of("")));
		form.add(new AjaxSubmitLink("send", form)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				EventBus.get()
					.post(new ChatMessage(receiver.getModelObject(), input.getModelObject()));
			}
		});

		setVersioned(false);
	}

	@Subscribe(contextAwareFilter = ReceiverFilter.class)
	public void receiveMessage(AjaxRequestTarget target, ChatMessage message)
	{
		messageLabel.setDefaultModelObject(message.getMessage());
		target.add(messageLabel);
	}

	@Subscribe
	public void updateTime(AjaxRequestTarget target, Date event)
	{
		timeLabel.setDefaultModelObject(event.toString());
		target.add(timeLabel);
	}
}

