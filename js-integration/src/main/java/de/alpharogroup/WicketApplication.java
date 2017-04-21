package de.alpharogroup;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * 
 * @see de.alpharogroup.StartWicketAlertJsBehaviorExample#main(String[])
 */
public class WicketApplication extends WebApplication
{
	public static final int HTTP_PORT = 9090;
	public static final int HTTPS_PORT = 9443;

	public static WicketApplication get()
	{
		return (WicketApplication)Application.get();
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

		// add your configuration here
	}
}
