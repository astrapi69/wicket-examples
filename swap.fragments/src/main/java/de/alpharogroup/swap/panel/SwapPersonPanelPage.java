package de.alpharogroup.swap.panel;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

import de.alpharogroup.swap.PersonModel;
import de.alpharogroup.swap.panel.SwapPersonWithPanel;

public class SwapPersonPanelPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public SwapPersonPanelPage() {
		add(new SwapPersonWithPanel("personPanel", Model.of(new PersonModel())));
	}
}