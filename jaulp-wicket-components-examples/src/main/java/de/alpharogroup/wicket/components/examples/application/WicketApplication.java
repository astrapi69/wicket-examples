/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.examples.application;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.IApplicationListener;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.wicket.base.application.plugins.ApplicationDebugSettingsPlugin;
import de.alpharogroup.wicket.base.util.application.ApplicationExtensions;
import de.alpharogroup.wicket.bootstrap3.application.WicketBootstrap3Application;
import de.alpharogroup.wicket.components.examples.home.HomePage;
import net.ftlines.wicketsource.WicketSource;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 */
public class WicketApplication extends WicketBootstrap3Application
{
	public static final int DEFAULT_HTTP_PORT = 18080;
	public static final int DEFAULT_HTTPS_PORT = 18443;
	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(WicketApplication.class.getName());

	/**
	 * Gets the WicketApplication.
	 *
	 * @return the WicketApplication object.
	 */
	public static WicketApplication get()
	{
		return (WicketApplication)Application.get();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getDefaultHttpPort()
	{
		return WicketApplication.DEFAULT_HTTP_PORT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getDefaultHttpsPort()
	{
		return WicketApplication.DEFAULT_HTTPS_PORT;
	}

	/**
	 * Gets the domain name.
	 *
	 * @return the domain name
	 */
	public String getDomainName()
	{
		return "jaulp-wicket-components.com";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPackageToScan()
	{
		return ListExtensions.getFirst(newPackagesToScanAsList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] newPackagesToScan()
	{
		final String[] packagesToScan = { "de.alpharogroup.wicket.components.examples" };
		return packagesToScan;
	}

	/**
	 * Factory callback method that returns the packages to scan as a {@link List} object.
	 *
	 * @return the {@link List} with the packages to scan
	 */
	protected List<String> newPackagesToScanAsList()
	{
		return Arrays.asList(newPackagesToScan());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Session newSession(final Request request, final Response response)
	{
		final WicketSession session = new WicketSession(request);
		session.bind();
		LOGGER.info("new session:" + session.getId());
		return session;
	}

	/**
	 * Called just before a the application configurations.
	 */
	@Override
	protected void onBeforeApplicationConfigurations()
	{
		super.onBeforeApplicationConfigurations();
		// Add a custom resource loader for ResourceBundles...
		// getResourceSettings().getStringResourceLoaders().add(
		// new BundleStringResourceLoader(MessageSource.class.getName()));
	}

	@Override
	protected void onDeploymentModeSettings()
	{
		super.onDeploymentModeSettings();
		ApplicationExtensions.setDefaultDeploymentModeConfiguration(this,
			new ApplicationRequestCycleListener());
	}

	@Override
	protected void onDevelopmentModeSettings()
	{
		super.onDevelopmentModeSettings();
		// Demonstration how to install the debug plugin...
		new ApplicationDebugSettingsPlugin()
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onConfigure(final WebApplication application)
			{
				super.onConfigure(application);
				// Adds the references from source code to the browser to reference in eclipse....
				WicketSource.configure(application);
			};
		}.install(this);

		// add an applicationListener...
		this.getApplicationListeners().add(new IApplicationListener()
		{
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onAfterInitialized(final Application application)
			{
				LOGGER.info("Wicket application is initialized");
				// here can comes code that is needed after the application
				// initialization...
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onBeforeDestroyed(final Application application)
			{
				LOGGER.info("Wicket application is destroyed");
				// here can comes code that is needed before the application
				// been destroyed...
			}
		});
		// strip wicket tags...
		this.getMarkupSettings().setStripWicketTags(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onGlobalSettings()
	{
		super.onGlobalSettings();
		ApplicationExtensions.setGlobalSettings(this, newHttpPort(), newHttpsPort(),
			FOOTER_FILTER_NAME, "UTF-8", "+*.css", "+*.png", "+*.woff2", "+*.js.map");
	}

}
