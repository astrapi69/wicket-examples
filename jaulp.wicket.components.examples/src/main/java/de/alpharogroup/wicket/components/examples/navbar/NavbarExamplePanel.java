package de.alpharogroup.wicket.components.examples.navbar;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.bootstrap3.components.navbar.brand.NavbarBrandPanel;
import de.alpharogroup.wicket.bootstrap3.components.navbar.dropdown.NavbarDropdownPanel;
import de.alpharogroup.wicket.components.examples.imprint.ImprintPage;
import de.alpharogroup.wicket.components.examples.termofuse.TermOfUsePage;
import de.alpharogroup.wicket.components.link.DefaultTargets;
import de.alpharogroup.wicket.components.link.LinkItem;

public class NavbarExamplePanel extends Panel {

	private static final long serialVersionUID = 1L;

	public NavbarExamplePanel(final String id, final IModel<?> model) {
		super(id, model);
		add(new NavbarBrandPanel("brandPanel", model)
		{
			@Override
			protected AbstractLink newNavbarBrandLink(final String id)
			{
				final AjaxLink<Void> link = (AjaxLink<Void>)super.newNavbarBrandLink(id);
				link.setBody(Model.of("Brand a"));
				return link;
			}

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				// TODO Auto-generated method stub

			}
		});

		add(new NavbarDropdownPanel("dropdownPanel", newNavbarLinkItems(), Model.of("Legal")));
	}

	/**
	 * Factory method for creating a new list with the link items for the nav bar area.
	 *
	 * @return the new <code>{@link IModel}</code> with the link items for the nav bar area.
	 */
	protected IModel<List<LinkItem>> newNavbarLinkItems()
	{
		final List<LinkItem> linkModel = new ArrayList<>();
		linkModel.add(LinkItem
			.builder()
			.url("http://www.alpharogroup.de/")
			.target(DefaultTargets.BLANK.getTarget())
			// open in a new tab or window...
			.linkClass(ExternalLink.class)
			.resourceModelKey(
				ResourceBundleKey.builder().key("main.footer.copyright.label")
					.defaultValue("\u0040 copyright 2012 Design by Alpha Ro Group")
					.build())
			.build());
		linkModel.add(LinkItem
			.builder()
			.pageClass(ImprintPage.class)
			.resourceModelKey(
				ResourceBundleKey.builder().key("main.global.menu.masthead.label")
					.defaultValue("Imprint").build()).build());
		linkModel.add(LinkItem
			.builder()
			.pageClass(TermOfUsePage.class)
			.resourceModelKey(
				ResourceBundleKey.builder().key("main.global.menu.term.of.use.label")
					.defaultValue("AGBs").build()).build());
		final IModel<List<LinkItem>> listModel = new ListModel<>(linkModel);
		return listModel;
	}

}
