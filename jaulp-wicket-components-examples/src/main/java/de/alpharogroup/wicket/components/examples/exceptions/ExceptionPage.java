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
package de.alpharogroup.wicket.components.examples.exceptions;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.exception.ExceptionExtensions;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.home.HomePage;
import de.alpharogroup.wicket.components.report.ReportThrowableModelBean;
import de.alpharogroup.wicket.components.report.ReportThrowablePanel;

@MountPath("public/exception")
public class ExceptionPage extends PubliclyBasePage<ReportThrowableModelBean>
{
	private static final long serialVersionUID = 1L;

	public ExceptionPage()
	{
		this(
			Model
				.of(ReportThrowableModelBean.builder()
					.throwable(new IllegalArgumentException("exception example..."))
					.affectedUsername("test user").description("test description")
					.affectedUsername("test username")
					.originalResponse(RequestCycle.get().getOriginalResponse().toString())
					.responsePage(HomePage.class)
					.stackTrace(ExceptionExtensions
						.getStackTrace(new IllegalArgumentException("exception example...")))
					.build()));
	}

	public ExceptionPage(final IModel<ReportThrowableModelBean> model)
	{
		super(model);
	}

	@Override
	public Panel newContainerPanel(final String id, final IModel<ReportThrowableModelBean> model)
	{

		return new ReportThrowablePanel(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmitError(final AjaxRequestTarget target)
			{
				setResponsePage(model.getObject().getResponsePage());
			}
		};
	}
}