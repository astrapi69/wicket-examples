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

import org.apache.log4j.Logger;
import org.apache.wicket.util.time.Duration;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.WicketJetty9Runner;
import de.alpharogroup.jetty9.runner.config.StartConfig;
import de.alpharogroup.log.LoggerExtensions;
import de.alpharogroup.resourcebundle.config.ConfigurationPropertiesResolver;
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
		final String projectName = "jaulp.wicket.components.examples";
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
