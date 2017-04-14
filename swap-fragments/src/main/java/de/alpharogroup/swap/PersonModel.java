package de.alpharogroup.swap;

import java.io.Serializable;

public class PersonModel implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private String gender;

	private String age;

	public String getAge()
	{
		return age;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getGender()
	{
		return gender;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

}
