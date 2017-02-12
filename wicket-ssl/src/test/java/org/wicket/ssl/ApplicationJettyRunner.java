package org.wicket.ssl;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import de.alpharogroup.file.delete.DeleteFileExtensions;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.config.FilterHolderConfiguration;
import de.alpharogroup.jetty9.runner.config.InitParameters;
import de.alpharogroup.jetty9.runner.config.Jetty9RunConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletContextHandlerConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletHolderConfiguration;
import de.alpharogroup.jetty9.runner.factories.ServletContextHandlerFactory;
import de.alpharogroup.log.LoggerExtensions;
import de.alpharogroup.resourcebundle.config.ConfigurationPropertiesResolver;
import de.alpharogroup.resourcebundle.properties.PropertiesExtensions;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(LoggerExtensions.class)
public class ApplicationJettyRunner
{

	public static void main(final String[] args) throws Exception
	{
		final int sessionTimeout = (int) Duration.minutes(30).seconds();// set timeout to 30min(60sec * 30min=1800sec)...
		System.setProperty("wicket.configuration", "deployment");
		final String projectname = "wicket-ssl";
		final File projectDirectory = PathFinder.getProjectDirectory();
		final File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
			"webapp");

		final String filterPath = "/*";

		final File logfile = new File(projectDirectory, "application.log");
		if(logfile.exists()) {
			try {
				DeleteFileExtensions.delete(logfile);
			} catch (final IOException e) {
				Logger.getRootLogger().error("logfile could not deleted.", e);
			}
		}
		final String absolutePathFromLogfile = logfile.getAbsolutePath();
		// Add a file appender to the logger programatically
		Logger.getRootLogger().addFileAppender(LoggerExtensions.newFileAppender(absolutePathFromLogfile));

		final ServletContextHandler servletContextHandler = ServletContextHandlerFactory.getNewServletContextHandler(
			ServletContextHandlerConfiguration.builder()
			.filterHolderConfiguration(
				FilterHolderConfiguration.builder()
					.filterClass(WicketFilter.class)
					.name(projectname)
					.filterPath(filterPath)
					.initParameter(WicketFilter.FILTER_MAPPING_PARAM, filterPath)
					.initParameter(InitParameters.APPLICATION_FACTORY_CLASS_NAME,
						InitParameters.WICKET_SPRING_WEB_APPLICATION_FACTORY_CLASS)
					.initParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM,
						WicketApplication.class.getName())
					.build())
			.servletHolderConfiguration(
				ServletHolderConfiguration.builder()
					.servletClass(DefaultServlet.class)
					.pathSpec(filterPath)
					.build())
			.contextPath("/")
			.webapp(webapp)
			.maxInactiveInterval(sessionTimeout)
			.filterPath(filterPath)
			.initParameter("contextConfigLocation",
				"classpath:applicationContext.hbm.xml\nclasspath:applicationContext.xml")
			.initParameter("configuration", "deployment")
			.build());

		final Jetty9RunConfiguration configuration = newJetty9RunConfiguration(servletContextHandler);

		final Server server = new Server();
		Jetty9Runner.runServletContextHandler(server, configuration);

	}

	/**
	 * Factory method for create the {@link Jetty9RunConfiguration} and set the ports from the
	 * config file or take the default if in configfile is not set.
	 *
	 * @param servletContextHandler
	 *            the servlet context handler
	 *
	 * @return the new {@link Jetty9RunConfiguration}.
	 */
	private static Jetty9RunConfiguration newJetty9RunConfiguration(
		final ServletContextHandler servletContextHandler)
	{

		final ConfigurationPropertiesResolver configurationPropertiesResolver =
			new ConfigurationPropertiesResolver(
				WicketApplication.DEFAULT_HTTP_PORT,
				WicketApplication.DEFAULT_HTTPS_PORT,
				ConfigurationPropertiesResolver.DEFAULT_CONFIGURATION_PROPERTIES_FILENAME);

		final Jetty9RunConfiguration configuration = Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler)
			.httpPort(configurationPropertiesResolver.getHttpPort())
			.httpsPort(configurationPropertiesResolver.getHttpsPort())
			.keyStorePassword("wicket")
			.keyStorePathResource("/keystore")
			.build();

		return configuration;
	}

	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected static String getProjectName() throws IOException {
		final Properties projectProperties = PropertiesExtensions.loadProperties("project.properties");
		final String projectName = projectProperties.getProperty("artifactId");
		return projectName;
	}

}
