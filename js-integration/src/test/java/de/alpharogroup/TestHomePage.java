package de.alpharogroup;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Test
	public void homepageRendersSuccessfully()
	{
		// start and render the test page
		tester.startPage(HomePage.class);

		// assert rendered page class
		tester.assertRenderedPage(HomePage.class);
	}

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}
}
