package org.rest.annotations;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.rest.annotations.resources.EmployeeResource;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
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


		mountResource("/employeesmanager", new ResourceReference("restReference") {
			EmployeeResource resource = new EmployeeResource();
			@Override
			public IResource getResource() {
				return resource;
			}

		});
	}
}
