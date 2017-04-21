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
package org.wicket.ssl.pages.home;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicket.ssl.pages.http.NormalHttpPage;
import org.wicket.ssl.pages.https.NormalSecuredHttpsPage;
import org.wicket.ssl.pages.mountedHttps.MountedHttpsPage;

import de.alpharogroup.web.css.MainFoobarCssResourceReference;

/**
 * Homepage
 */
public class HomePage extends WebPage
{

	private static final long serialVersionUID = 1L;

	public HomePage()
	{
		super();
		initLayout();
	}

	// TODO Add any page properties or variables here

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public HomePage(final PageParameters parameters)
	{
		super(parameters);

		initLayout();

	}

	private void initLayout()
	{

		add(new Link<Void>("normalHttpLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(NormalHttpPage.class);
			}
		});

		add(new Link<Void>("normalSecureHttpsLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(NormalSecuredHttpsPage.class);
			}
		});

		add(new Link<Void>("mountedPathSecureHttpsLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(MountedHttpsPage.class);
			}
		});

	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		// PackageResourceReference cssFile = new PackageResourceReference(WebResources.class,
		// "main-foobar.css");
		 CssHeaderItem cssItem = CssHeaderItem.forReference(MainFoobarCssResourceReference.get());
		 response.render(cssItem);
	}
}
