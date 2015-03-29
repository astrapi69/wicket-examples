package org.rest.annotations.resources;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.sourceforge.jaulp.xml.json.JsonTransformer;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
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
	public void testCreate() throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:8080/employeesmanager/create");
		String jsonString = "{\"id\":\"26\",\"person\":{\"married\":true,\"nickname\":\"beast\",\"name\":\"Anna\",\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\"}}";
		Employee expected = JsonTransformer.toObject(jsonString, Employee.class);
		// Add authorization base64...
		Base64 b = new Base64();
		String encoding = b.encodeAsString(new String("wicket:wicket").getBytes());
		post.addHeader("Authorization", "Basic " + encoding);


		StringEntity input = new StringEntity(jsonString);
		input.setContentType("application/json");
		post.setEntity(input);
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
			.getContent()));
		String json = IOUtils.toString(rd);
		Employee actual = JsonTransformer.toObject(json, Employee.class);
		assertTrue(expected.equals(actual));

		HttpDelete delete = new HttpDelete("http://localhost:8080/employeesmanager/delete/26");

		client.execute(delete);
	}

	@Test
	public void testUpdate() throws ClientProtocolException, IOException, InterruptedException,
		ExecutionException
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:8080/employeesmanager/create");
		String jsonString = "{\"id\":\"26\",\"person\":{\"married\":true,\"nickname\":\"beast\",\"name\":\"Anna\",\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\"}}";

		// Add authorization base64...
		Base64 b = new Base64();
		String encoding = b.encodeAsString(new String("wicket:wicket").getBytes());
		post.addHeader("Authorization", "Basic " + encoding);

		StringEntity input = new StringEntity(jsonString);
		input.setContentType("application/json");
		post.setEntity(input);
		client.execute(post);

		client = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut("http://localhost:8080/employeesmanager/update");

		jsonString = "{\"id\":\"26\",\"person\":{\"married\":false,\"nickname\":\"beast\",\"name\":\"Anna\",\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\"}}";
		Employee expected = JsonTransformer.toObject(jsonString, Employee.class);
		input = new StringEntity(jsonString);
		input.setContentType("application/json");
		put.setEntity(input);
		client.execute(put);
		//
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Future<Response> f = asyncHttpClient.prepareGet(
			"http://localhost:8080/employeesmanager/read/26").execute();
		Response r = f.get();
		String responseString = r.getResponseBody();
		asyncHttpClient.close();
		Employee actual = JsonTransformer.toObject(responseString, Employee.class);
		assertTrue(expected.equals(actual));
		// clean up...
		HttpDelete delete = new HttpDelete("http://localhost:8080/employeesmanager/delete/26");

		client.execute(delete);
	}

	@Test
	public void testDelete() throws ClientProtocolException, IOException
	{
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("http://localhost:8080/employeesmanager/create");
		String jsonString = "{\"id\":\"26\",\"person\":{\"married\":true,\"nickname\":\"beast\",\"name\":\"Anna\",\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\"}}";
		Employee expected = JsonTransformer.toObject(jsonString, Employee.class);

		// Add authorization base64...
		Base64 b = new Base64();
		String encoding = b.encodeAsString(new String("wicket:wicket").getBytes());
		post.addHeader("Authorization", "Basic " + encoding);

		StringEntity input = new StringEntity(jsonString);
		input.setContentType("application/json");
		post.setEntity(input);
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity()
			.getContent()));
		String json = IOUtils.toString(rd);
		Employee actual = JsonTransformer.toObject(json, Employee.class);
		assertTrue(expected.equals(actual));

		HttpDelete delete = new HttpDelete("http://localhost:8080/employeesmanager/delete/26");

		response = client.execute(delete);


	}

}
