package de.alpharogroup.swap;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

public class PersonPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public PersonPage() {
		add(new PersonPanel("personPanel", Model.of(new PersonModel())));
	}
}