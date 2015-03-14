package org.rest.annotations.resources;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.sourceforge.jaulp.xml.json.JsonTransformer;

import org.jaulp.test.objects.Employee;
import org.jaulp.test.objects.Gender;
import org.jaulp.test.objects.Person;
import org.junit.Test;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class EmployeeResourceTest
{
	/** The employees. */
	private final List<Employee> employees;
	{

		employees = new ArrayList<>();
		employees.add(Employee
			.builder()
			.person(
				Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
					.about("Ha ha ha...").nickname("beast").build()).id("23").build());
		employees.add(Employee
			.builder()
			.person(
				Person.builder().gender(Gender.MALE).name("Andreas").married(false)
					.about("fine person").nickname("cute").build()).id("24").build());
		employees.add(Employee
			.builder()
			.person(
				Person.builder().gender(Gender.FEMALE).name("Tatjana").married(false)
					.about("Im hot").nickname("beautiful").build()).id("25").build());
	}

	@Test
	public void testGetAll() throws InterruptedException, ExecutionException, IOException
	{
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Future<Response> f = asyncHttpClient.prepareGet(
			"http://localhost:8080/employeesmanager/employees").execute();
		Response r = f.get();
		String responseString = r.getResponseBody();
		asyncHttpClient.close();
		List<Employee> actual = JsonTransformer.toObjectList(responseString, Employee.class);
		for (Employee employee : actual)
		{
			assertTrue(employees.contains(employee));
		}
	}

	@Test
	public void testRead() throws InterruptedException, ExecutionException, IOException
	{
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Future<Response> f = asyncHttpClient.prepareGet(
			"http://localhost:8080/employeesmanager/read/24").execute();
		Response r = f.get();
		String responseString = r.getResponseBody();
		asyncHttpClient.close();
		Employee actual = JsonTransformer.toObject(responseString, Employee.class);
		for (Employee employee : employees)
		{
			if (employee.getId().equals("24"))
			{
				assertTrue(employee.equals(actual));
			}
		}
	}

	@Test
	public void testCreate()
	{

	}

	@Test
	public void testUpdate()
	{

	}

	@Test
	public void testDelete()
	{

	}

}
