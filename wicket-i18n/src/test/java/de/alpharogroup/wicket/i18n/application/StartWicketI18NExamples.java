package de.alpharogroup.wicket.i18n.application;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.wicket.util.time.Duration;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.WicketJetty9Runner;
import de.alpharogroup.jetty9.runner.config.StartConfig;
import de.alpharogroup.log.LoggerExtensions;
import de.alpharogroup.wicket.base.application.ConfigurationPropertiesResolver;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(LoggerExtensions.class)
public class StartWicketI18NExamples
{
	/** The logger constant. */
	protected static final Logger LOG = Logger.getLogger(StartWicketI18NExamples.class.getName());

	public static void main(final String[] args)
	{
		final ConfigurationPropertiesResolver configurationPropertiesResolver = new ConfigurationPropertiesResolver(
			WicketApplication.DEFAULT_HTTP_PORT, WicketApplication.DEFAULT_HTTPS_PORT,
			ConfigurationPropertiesResolver.DEFAULT_CONFIGURATION_PROPERTIES_FILENAME);
		startWicketApplication(configurationPropertiesResolver);
	}

	private static void startWicketApplication(final ConfigurationPropertiesResolver configurationPropertiesResolver)
	{
		final String projectName = "wicket-i18n";
		final File projectDirectory = PathFinder.getProjectDirectory();
		final File webapp = Jetty9Runner.getWebappDirectory(projectDirectory, projectName);
		final File logFile = Jetty9Runner.getLogFile(projectDirectory, "application.log");

		final StartConfig startConfig = StartConfig.builder().applicationName(WicketApplication.class.getName())
			.contextPath("/")
			.filterPath("/*")
			.httpPort(configurationPropertiesResolver.getHttpPort())
			.httpsPort(configurationPropertiesResolver.getHttpsPort())
			.keyStorePassword("wicket")
			.keyStorePathResource("/keystore")
			.projectDirectory(projectDirectory)
			.projectName(projectName)
			.runtimeConfigurationType("deployment")
			.sessionTimeout((int)Duration.minutes(1).seconds())
			.webapp(webapp)
			.logFile(logFile)
			.absolutePathFromLogfile(logFile.getAbsolutePath())
			.build();

		WicketJetty9Runner.run(startConfig);
	}

}
