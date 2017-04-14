package org.wicket.ssl;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.wicket.ssl.pages.home.HomePage;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * 
 * @see org.wicket.ssl.StartSslExamples#main(String[])
 */
public class WicketApplication extends WebApplication
{
	public static final int DEFAULT_HTTP_PORT = 8080;
	public static final int DEFAULT_HTTPS_PORT = 8443;

	/**
	 * Sets the root request mapper for the given application from the given httpPort and httpsPort.
	 *
	 * @param application
	 *            the application
	 * @param httpPort
	 *            the http port
	 * @param httpsPort
	 *            the https port
	 */
	public static void setRootRequestMapper(final Application application, final int httpPort,
		final int httpsPort)
	{
		application.setRootRequestMapper(new HttpsMapper(application.getRootRequestMapper(),
			new HttpsConfig(httpPort, httpsPort)));
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		setRootRequestMapper(this, DEFAULT_HTTP_PORT, DEFAULT_HTTPS_PORT);
		// setRootRequestMapper(new HttpsMapper(getRootRequestMapper(),
		// new HttpsConfig(DEFAULT_HTTP_PORT, DEFAULT_HTTPS_PORT)));
	}
}
