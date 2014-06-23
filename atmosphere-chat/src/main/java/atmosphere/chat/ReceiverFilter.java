package atmosphere.chat;

import org.apache.wicket.Session;
import org.apache.wicket.atmosphere.AtmosphereEvent;

import com.google.common.base.Predicate;

public class ReceiverFilter implements Predicate<AtmosphereEvent>
{
	public ReceiverFilter()
	{
	}

	@Override
	public boolean apply(AtmosphereEvent input)
	{
		if (input.getPayload() instanceof ChatMessage)
		{
			ChatMessage msg = (ChatMessage)input.getPayload();
			return msg.getReceiver() == null || msg.getReceiver().isEmpty() ||
				msg.getReceiver().equals(Session.get().getId());
		}
		return false;
	}
}