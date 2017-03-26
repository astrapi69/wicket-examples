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
package de.alpharogroup.wicket.components.examples;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.wicket.util.time.Duration;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.WicketJetty9Runner;
import de.alpharogroup.jetty9.runner.config.StartConfig;
import de.alpharogroup.log.LoggerExtensions;
import de.alpharogroup.resourcebundle.config.ConfigurationPropertiesResolver;
import de.alpharogroup.resourcebundle.properties.PropertiesExtensions;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod(LoggerExtensions.class)
public class StartComponentExamples
{
	/** The logger constant. */
	protected static final Logger LOG = Logger.getLogger(StartComponentExamples.class.getName());

	public static void main(final String[] args)
	{
		final ConfigurationPropertiesResolver configurationPropertiesResolver = new ConfigurationPropertiesResolver(
			WicketApplication.DEFAULT_HTTP_PORT, WicketApplication.DEFAULT_HTTPS_PORT,
			ConfigurationPropertiesResolver.DEFAULT_CONFIGURATION_PROPERTIES_FILENAME);
		startWicketApplication(configurationPropertiesResolver);
	}

	private static void startWicketApplication(final ConfigurationPropertiesResolver configurationPropertiesResolver)
	{
		final int sessionTimeout = (int) Duration.minutes(30).seconds();// set timeout to 30min(60sec * 30min=1800sec)...

		String configurationType;
		// comment development and uncomment deployment if you want to run in deployment mode
		configurationType = "development";
		// configurationType = "deployment";
		System.setProperty("wicket.configuration", configurationType);
		final String projectName = getProjectNameQuietly("jaulp.wicket.components.examples");
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
			.runtimeConfigurationType(configurationType)
			.sessionTimeout(sessionTimeout)
			.webapp(webapp)
			.logFile(logFile)
			.absolutePathFromLogfile(logFile.getAbsolutePath())
			.build();

		WicketJetty9Runner.run(startConfig);
	}

	/**
	 * Gets the project name from the 'project.properties'. In this properties file is only a
	 * reference of the artifactId from the pom.
	 *
	 * @return the project name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected static String getProjectName() throws IOException
	{
		final Properties projectProperties = PropertiesExtensions
			.loadProperties("project.properties");
		if (projectProperties != null)
		{
			final String projectName = projectProperties.getProperty("artifactId");
			if (projectName == null)
			{
				throw new RuntimeException(
					"No properties key 'artifactId' found in the properties file project.properties exist.");
			}
			return projectName;
		}
		else
		{
			throw new RuntimeException("No properties file project.properties exist.");
		}
	}

	/**
	 * Gets the project name from the 'project.properties'. In this properties file is only a
	 * reference of the artifactId from the pom.
	 *
	 * @param defaultName
	 *            the default project name if
	 * @return the project name
	 */
	protected static String getProjectNameQuietly(final String defaultName)
	{
		try
		{
			getProjectName();
		}
		catch (final Exception e)
		{
			// default project name will be returned...
			e.printStackTrace();
		}
		return defaultName;
	}

}
