package org.rest.annotations.resources;

import java.util.ArrayList;
import java.util.List;

import org.jaulp.test.objects.Employee;
import org.jaulp.test.objects.Gender;
import org.jaulp.test.objects.Person;
import org.wicketstuff.rest.annotations.MethodMapping;
import org.wicketstuff.rest.contenthandling.json.objserialdeserial.JacksonObjectSerialDeserial;
import org.wicketstuff.rest.contenthandling.json.webserialdeserial.JsonWebSerialDeserial;
import org.wicketstuff.rest.resource.AbstractRestResource;

public class EmployeeResource extends AbstractRestResource<JsonWebSerialDeserial>
{
	private final List<Employee> employees;
	{

		employees = new ArrayList<>();
		employees.add(Employee.builder()
			.person(Person.builder()
							.gender(Gender.FEMALE)
							.name("Anna")
							.married(true)
							.about("Ha ha ha...")
							.nickname("beast")
							.build())
			.id("23")
			.build());
		employees.add(Employee.builder()
			.person(Person.builder()
							.gender(Gender.MALE)
							.name("Andreas")
							.married(false)
							.about("fine person")
							.nickname("cute")
							.build())
			.id("24")
			.build());
		employees.add(Employee.builder()
			.person(Person.builder()
							.gender(Gender.FEMALE)
							.name("Tatjana")
							.married(false)
							.about("Im hot")
							.nickname("beautiful")
							.build())
			.id("25")
			.build());
	}

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeResource()
	{
		super(new JsonWebSerialDeserial(new JacksonObjectSerialDeserial()));
	}

	public EmployeeResource(JsonWebSerialDeserial serialDeserial)
	{
		super(serialDeserial);
	}

    @MethodMapping("/employees")
	public List<Employee> getAll() {
		return employees;
	}

}
