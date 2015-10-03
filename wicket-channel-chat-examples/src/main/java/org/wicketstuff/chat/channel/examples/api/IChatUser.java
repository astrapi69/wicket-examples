package org.wicketstuff.chat.channel.examples.api;

import java.io.Serializable;

/**
 * The Interface IChatUser.
 */
public interface IChatUser extends Serializable
{

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	String getUsername();

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	void setUsername(final String username);

}