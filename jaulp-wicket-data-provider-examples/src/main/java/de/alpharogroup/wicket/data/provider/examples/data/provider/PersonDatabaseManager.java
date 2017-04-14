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
package de.alpharogroup.wicket.data.provider.examples.data.provider;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import de.alpharogroup.date.DatePatterns;
import de.alpharogroup.date.ParseDateExtensions;

public final class PersonDatabaseManager
{


	public static final PersonDatabaseManager instance = new PersonDatabaseManager();

	public static PersonDatabaseManager getInstance()
	{
		return instance;
	}

	private List<Person> persons;

	private PersonDatabaseManager()
	{
	}

	/**
	 * Gets the persons.
	 *
	 * @return the persons
	 */
	public List<Person> getPersons()
	{
		if (persons == null)
		{
			persons = new ArrayList<Person>();
			try
			{
				persons.add(new Person("Jamie", "Curtis",
					ParseDateExtensions.parseToDate("12.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Toni", "Montana",
					ParseDateExtensions.parseToDate("02.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Anton", "Pitt",
					ParseDateExtensions.parseToDate("13.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Willy", "Lee",
					ParseDateExtensions.parseToDate("03.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Bruce", "Willis",
					ParseDateExtensions.parseToDate("14.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Henning", "Presley",
					ParseDateExtensions.parseToDate("04.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Michael", "Jackson",
					ParseDateExtensions.parseToDate("15.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Marco", "William",
					ParseDateExtensions.parseToDate("05.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Gabriel", "Spears",
					ParseDateExtensions.parseToDate("16.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Kurt", "Russell",
					ParseDateExtensions.parseToDate("06.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Ralph", "Crow",
					ParseDateExtensions.parseToDate("17.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Peter", "Reilly",
					ParseDateExtensions.parseToDate("07.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Asterix", "Nulty",
					ParseDateExtensions.parseToDate("08.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Obelix", "Bond",
					ParseDateExtensions.parseToDate("18.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Miraculix", "James",
					ParseDateExtensions.parseToDate("09.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Darth", "Schnyder",
					ParseDateExtensions.parseToDate("19.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Angela", "De Niro",
					ParseDateExtensions.parseToDate("10.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Brad", "Pacino",
					ParseDateExtensions.parseToDate("21.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
			}
			catch (final ParseException e)
			{
				e.printStackTrace();
			}
		}
		return persons;
	}
}
