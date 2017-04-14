package org.rest.annotations;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.rest.annotations.resources.EmployeeResource;
import org.rest.annotations.security.HttpBasicAuthRoleCheckingStrategy;

/**
 * Application object for your web application. If you want to run this application without
 * deploying, run the Start class.
 * 
 * @see org.rest.annotations.StartRestAnnotationsExamples#main(String[])
 */
public class WicketApplication extends WebApplication
{
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
		final HttpBasicAuthRoleCheckingStrategy roleCheckingStrategy = new HttpBasicAuthRoleCheckingStrategy(
			"wicket", "wicket");


		mountResource("/employeesmanager", new ResourceReference("restReference")
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;
			EmployeeResource resource = new EmployeeResource(roleCheckingStrategy);

			@Override
			public IResource getResource()
			{
				return resource;
			}

		});
	}
}
