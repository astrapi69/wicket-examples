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

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.RequestCycle;

import de.alpharogroup.exception.ExceptionExtensions;
import de.alpharogroup.wicket.base.application.AbstractApplicationRequestCycleListener;
import de.alpharogroup.wicket.components.examples.exceptions.ExceptionPage;
import de.alpharogroup.wicket.components.examples.home.HomePage;
import de.alpharogroup.wicket.components.report.ReportThrowableModelBean;

public class ApplicationRequestCycleListener extends AbstractApplicationRequestCycleListener
{
	private static final long serialVersionUID = 1L;

	@Override
	public IRequestablePage newExceptionPage(final RequestCycle cycle, final Exception e)
	{
		e.printStackTrace();

		final IModel<ReportThrowableModelBean> model = Model
			.of(ReportThrowableModelBean.builder().throwable(e).affectedUsername("test user") // set
																								// the
																								// appropriate
																								// affected
																								// username
				.description("test description") // set the appropriate description
				.rootUsername("test rootUsername") // set the appropriate root username
				.stackTrace(ExceptionExtensions.getStackTrace(e)).responsePage(HomePage.class)
				.originalResponse(cycle.getOriginalResponse().toString()).build());
		return new ExceptionPage(model);
	}
}