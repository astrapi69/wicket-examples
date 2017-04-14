package org.rest.annotations.resources;

import java.util.Arrays;
import java.util.Collections;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.wicketstuff.rest.utils.wicket.validator.RestValidationError;

import de.alpharogroup.test.objects.Employee;

public class EmployeeValidator implements IValidator<Employee>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void validate(IValidatable<Employee> validatable)
	{
		Employee employee = validatable.getValue();
		if (employee.getPerson().getName() == null)
		{
			validatable.error(new RestValidationError(Arrays.asList("no.name"),
				Collections.<String, Object> emptyMap(), "name"));
		}
	}

}
