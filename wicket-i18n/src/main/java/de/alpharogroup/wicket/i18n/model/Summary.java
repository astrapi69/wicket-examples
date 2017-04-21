package de.alpharogroup.wicket.i18n.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder(toBuilder = true)
@Data
public class Summary implements Serializable
{

	public static class SummaryBuilder
	{
		private MessagePreferences messagePreferences = MessagePreferences.LONG;
	}

	private static final long serialVersionUID = 1L;

	private String otherCount;

	private String title;

	private String titleUrl;
	private String rate;

	@NonNull
	private MessagePreferences messagePreferences = MessagePreferences.LONG;
}
