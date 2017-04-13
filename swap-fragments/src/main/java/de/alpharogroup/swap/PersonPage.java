package de.alpharogroup.swap;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

import de.alpharogroup.swap.panel.SwapPersonWithPanel;

public class PersonPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public PersonPage() {
		add(new SwapPersonWithPanel("personPanel", Model.of(new PersonModel())));
	}
}