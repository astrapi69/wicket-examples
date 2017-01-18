package de.alpharogroup.wicket.i18n.application;

import org.apache.wicket.Application;
import org.apache.wicket.IApplicationListener;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import de.alpharogroup.wicket.base.application.BaseWebApplication;
import de.alpharogroup.wicket.base.application.plugins.ApplicationDebugSettingsPlugin;
import de.alpharogroup.wicket.base.util.application.ApplicationExtensions;
import de.alpharogroup.wicket.i18n.pages.home.HomePage;

public class WicketApplication extends BaseWebApplication {

	// http://www.wicket-library.com/wicket-examples/resourceaggregation/wicket/bookmarkable/org.apache.wicket.examples.source.SourcesPage?0&SourcesPage_class=org.apache.wicket.examples.resourcedecoration.HomePage&source=HomePage.java
	public static final String FOOTER_FILTER_NAME = "footer-container";

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * Called just before a the application configurations.
	 */
	@Override
	protected void onBeforeApplicationConfigurations() {
		super.onBeforeApplicationConfigurations();
		// Add a custom resource loader for ResourceBundles...
		// getResourceSettings().getStringResourceLoaders().add(
		// new BundleStringResourceLoader(MessageSource.class.getName()));
	}

	@Override
	protected void onDeploymentModeSettings() {
		super.onDeploymentModeSettings();
	}

	@Override
	protected void onDevelopmentModeSettings() {
		super.onDevelopmentModeSettings();

		getApplicationSettings().setUploadProgressUpdatesEnabled(true);
		// Demonstration how to install the debug plugin...
		new ApplicationDebugSettingsPlugin() {
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onConfigure(final WebApplication application) {
				super.onConfigure(application);
			};
		}.install(this);

		// add an applicationListener...
		this.getApplicationListeners().add(new IApplicationListener() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onAfterInitialized(final Application application) {
				LOGGER.info("Wicket application is initialized");
				// here can comes code that is needed after the application
				// initialization...
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onBeforeDestroyed(final Application application) {
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
	protected void onGlobalSettings() {
		super.onGlobalSettings();
		ApplicationExtensions.setGlobalSettings(this, newHttpPort(), newHttpsPort(), FOOTER_FILTER_NAME, "UTF-8",
				"+*.css", "+*.png", "+*.otf", "+*.eot", "+*.svg", "+*.ttf", "+*.woff2", "+*.js.map");
	}
}
