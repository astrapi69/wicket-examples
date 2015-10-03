package org.wicketstuff.chat.channel.examples.application;

import java.io.IOException;
import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.chat.channel.TimerChannelService;
import org.wicketstuff.chat.channel.api.IChannelService;
import org.wicketstuff.chat.channel.examples.pages.Index;

import de.alpharogroup.wicket.PackageResourceReferences;

/**
 * Runs the WicketApplication when invoked from command line.
 */
public class WicketApplication extends WebApplication implements Serializable
{
	public static final int DEFAULT_HTTP_PORT = 9090;
	public static final int DEFAULT_HTTPS_PORT = 9443;
	private static final long serialVersionUID = 1L;

	private IChannelService timerChannelService;

	/**
	 * Constructor
	 */
	public WicketApplication()
	{
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Index.class;
	}

	public IChannelService getTimerChannelService()
	{
		// lazy init...
		if (timerChannelService == null)
		{
			timerChannelService = new TimerChannelService(Duration.seconds(1));
		}
		return timerChannelService;
	}


	@Override
	protected void init()
	{
		super.init();
		this.getMarkupSettings().setStripWicketTags(true); // IMPORTANT!
		try
		{
			initResources();
		}
		catch (final ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

	}


	/**
	 * Inits the all relevant resources like css and js files.
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void initResources() throws ClassNotFoundException, IOException
	{
		final PackageResourceReferences prr = PackageResourceReferences.getInstance();
		prr.initializeResources("org.wicketstuff");
	}

}