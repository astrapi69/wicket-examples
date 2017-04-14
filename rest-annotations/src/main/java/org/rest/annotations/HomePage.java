package org.rest.annotations;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.rest.annotations.panels.EmployeePanel;

import de.alpharogroup.test.objects.Employee;

public class HomePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters)
	{
		super(parameters);

		add(new EmployeePanel("employeePanel", Model.of(Employee.builder().build())));

	}
}
