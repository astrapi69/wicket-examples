package de.alpharogroup.wicket.i18n.model;

public enum MessagePreferences
{
	LONG, SHORT;
	public String getStyle()
	{
		return toString().toLowerCase();
	}
}
