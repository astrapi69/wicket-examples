package org.rest.annotations.resources;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.jaulp.test.objects.Employee;

public class EmployeeValidator implements IValidator<Employee>
{

	@Override
	public void validate(IValidatable<Employee> validatable)
	{
		Employee employee = validatable.getValue();
		// do validation...
	}

}
