package atmosphere.chat;

import java.io.Serializable;

public class ChatMessage implements Serializable
{

	private String receiver;
	private String message;

	public ChatMessage(String receiver, String message)
	{
		this.receiver = receiver;
		this.message = message;
	}

	public String getReceiver()
	{
		return receiver;
	}

	public String getMessage()
	{
		return message;
	}
}
