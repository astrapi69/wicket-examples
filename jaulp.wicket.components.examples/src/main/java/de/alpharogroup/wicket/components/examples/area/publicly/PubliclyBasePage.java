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
package de.alpharogroup.wicket.components.examples.area.publicly;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.examples.ajaxtabs.addtab.EditableAjaxTabbedPage;
import de.alpharogroup.wicket.components.examples.alerts.AlertsPage;
import de.alpharogroup.wicket.components.examples.animate.AnimationPage;
import de.alpharogroup.wicket.components.examples.basepage.ApplicationBasePage;
import de.alpharogroup.wicket.components.examples.beaneditor.example.BeanEditorExamplePage;
import de.alpharogroup.wicket.components.examples.buttons.ButtonsPage;
import de.alpharogroup.wicket.components.examples.captcha.ReCaptchaPage;
import de.alpharogroup.wicket.components.examples.captcha.SslReCaptchaPage;
import de.alpharogroup.wicket.components.examples.checkbox.CheckboxesPage;
import de.alpharogroup.wicket.components.examples.deregistration.DeregistrationPage;
import de.alpharogroup.wicket.components.examples.exceptions.ExceptionPage;
import de.alpharogroup.wicket.components.examples.fragment.replacewith.ReplaceWithPage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.AddressPage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.PersonPage;
import de.alpharogroup.wicket.components.examples.googlecharts.GoogleChartsExamplePage;
import de.alpharogroup.wicket.components.examples.home.HomePage;
import de.alpharogroup.wicket.components.examples.imprint.ImprintPage;
import de.alpharogroup.wicket.components.examples.labeled.LabeledComponentsPage;
import de.alpharogroup.wicket.components.examples.navbar.NavbarExamplePanel;
import de.alpharogroup.wicket.components.examples.notifications.NotificationExamplesPage;
import de.alpharogroup.wicket.components.examples.pdfdownload.PdfDownloadPage;
import de.alpharogroup.wicket.components.examples.popupoverlay.PopupoverlayPage;
import de.alpharogroup.wicket.components.examples.radios.RadioComponentsExamplePage;
import de.alpharogroup.wicket.components.examples.resource.loading.ResourceLoadingExamplesPage;
import de.alpharogroup.wicket.components.examples.sign.in.SigninPage;
import de.alpharogroup.wicket.components.examples.sign.up.SignupPage;
import de.alpharogroup.wicket.components.examples.socialnet.SocialNetworksExamplePage;
import de.alpharogroup.wicket.components.examples.termofuse.TermOfUsePage;
import de.alpharogroup.wicket.components.examples.tooltips.TooltipsExamplePage;
import de.alpharogroup.wicket.components.examples.urls.WicketUrlPage;

/**
 * The class {@link PubliclyBasePage}.
 *
 * @author Asterios Raptis
 * @param <T>
 *            the generic type of the page model
 */
public abstract class PubliclyBasePage<T> extends ApplicationBasePage<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The logger constant. */
	protected static final Logger LOG = Logger.getLogger(PubliclyBasePage.class.getName());

	/**
	 * Default constructor that instantiates a new {@link PubliclyBasePage}.
	 */
	public PubliclyBasePage()
	{
		this(new PageParameters());
	}

	/**
	 * Instantiates a new {@link PubliclyBasePage} with the given model.
	 *
	 * @param model the model
	 */
	public PubliclyBasePage(final IModel<T> model)
	{
		super(model);
	}

	/**
	 * Instantiates a new {@link PubliclyBasePage} with the given {@link PageParameters} object.
	 *
	 * @param parameters
	 *            the {@link PageParameters} object
	 */
	public PubliclyBasePage(final PageParameters parameters)
	{
		super(parameters);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(newNavbarPanel(NAVBAR_PANEL_ID, getModel()));
		add(feedback = newFeedbackPanel(FEEDBACK_PANEL_ID, getModel()));
		add(new NavbarExamplePanel("newnav", Model.of("")));
		add(newContainerPanel(CONTAINER_PANEL_ID, getModel()));
		add(newFooterPanel(FOOTER_PANEL_ID, getModel()));
	}

	/**
	 * Factory method for creating a new {@link Component} for the navigation menu. This method is invoked in the
	 * constructor from the derived classes and have to be overridden so users can provide their own
	 * version of a new {@link Component} for the navigation menu.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Component} for the navigation menu.
	 */
	protected Component newNavbarPanel(final String id, final IModel<T> model) {
		return newNavbar(id);
	}

	/**
	 * Factory method for creating a new {@link Navbar} instance.
	 *
	 * @param markupId
	 *            The components markup id.
	 * @return a new {@link Navbar} instance
	 */
	protected Navbar newNavbar(final String markupId)
	{
		final Navbar navbar = new Navbar(markupId);

        navbar.setPosition(Navbar.Position.TOP);
        navbar.setInverted(true);

		final IModel<String> brandNameModel = ResourceModelFactory.newResourceModel(
			"global.slogan.mainhead.label", this);
		final IModel<String> overviewModel = ResourceModelFactory.newResourceModel(
			"global.menu.overview.label", this);
		// show brand name
		navbar.setBrandName(brandNameModel);

		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
			new NavbarButton<Void>(HomePage.class, overviewModel).setIconType(GlyphIconType.home),
			newLegalDropDownButton(),
			newThemesNavbarDropDownButton())
				);

        navbar.addComponents(new ImmutableNavbarComponent(newFeaturesDropDownButton(), Navbar.ComponentPosition.RIGHT));

		return navbar;
	}

	/**
	 * Factory method for creating a new dropdown navbar item for the features.
	 *
	 * @return the component
	 */
	protected Component newFeaturesDropDownButton()
	{
		final IModel<String> featuresMainModel = ResourceModelFactory.newResourceModel(
			"global.menu.features.label", this);
		final IModel<String> swapModel = ResourceModelFactory.newResourceModel(
			"global.menu.swap.label", this);
		final IModel<String> swapPersonModel = ResourceModelFactory.newResourceModel(
			"global.menu.swap.person.label", this);
		final IModel<String> replaceWithPanelModel = ResourceModelFactory.newResourceModel(
			"global.menu.replace.with.panel.label", this);
		final IModel<String> popupoverlayPanelModel = ResourceModelFactory.newResourceModel(
			"global.menu.popupoverlay.label", this);
		final IModel<String> tabsModel = ResourceModelFactory.newResourceModel(
			"global.menu.tabs.label", this);
		final IModel<String> signInModel = ResourceModelFactory.newResourceModel(
			"global.menu.sign.in.label", this);
		final IModel<String> signUpModel = ResourceModelFactory.newResourceModel(
			"global.menu.sign.up.label", this);
		final IModel<String> downloadFileModel = ResourceModelFactory.newResourceModel(
			"global.menu.download.pdf.label", this);
		final IModel<String> recaptchaModel = ResourceModelFactory.newResourceModel(
			"global.menu.recaptcha.label", this);
		final IModel<String> sslRecaptchaModel = ResourceModelFactory.newResourceModel(
			"global.menu.ssl.recaptcha.label", this);
		final IModel<String> wicketUrlsModel = ResourceModelFactory.newResourceModel(
			"global.menu.wicket.urls.label", this);
		final IModel<String> alertsModel = ResourceModelFactory.newResourceModel(
			"global.menu.alerts.label", this);
		final IModel<String> labeledModel = ResourceModelFactory.newResourceModel(
			"global.menu.labeled.label", this);
		final IModel<String> buttonsModel = ResourceModelFactory.newResourceModel(
			"global.menu.buttons.label", this);
		final IModel<String> checkboxesModel = ResourceModelFactory.newResourceModel(
			"global.menu.checkboxes.label", this);
		final IModel<String> radiosModel = ResourceModelFactory.newResourceModel(
			"global.menu.radios.label", this);
		final IModel<String> deregistrationModel = ResourceModelFactory.newResourceModel(
			"global.menu.deregistration.label", this);
		final IModel<String> exceptionModel = ResourceModelFactory.newResourceModel(
			"global.menu.exception.label", this);
		final IModel<String> beanEditorExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.bean.editor.label", this);
		final IModel<String> animationExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.animation.label", this);
		final IModel<String> googlechartsExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.googlecharts.label", this);
		final IModel<String> toastrExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.toastr.label", this);
		final IModel<String> socialNetExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.social.network.label", this);
		final IModel<String> resourceLoadingExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.resource.loading.label", this);
		final IModel<String> tooltipsExampleModel = ResourceModelFactory.newResourceModel(
			"global.menu.tooltips.label", this);
		return new NavbarDropDownButton(featuresMainModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId)
			{
				final List<AbstractLink> subMenu = new ArrayList<>();
				subMenu.add(new MenuBookmarkablePageLink<Void>(AddressPage.class, swapModel)
					.setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<Void>(PersonPage.class,
					swapPersonModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					BeanEditorExamplePage.class, beanEditorExampleModel)
					.setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<Void>(AnimationPage.class,
					animationExampleModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					GoogleChartsExamplePage.class, googlechartsExampleModel)
					.setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<Void>(ReplaceWithPage.class,
					replaceWithPanelModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<Void>(PopupoverlayPage.class,
					popupoverlayPanelModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					EditableAjaxTabbedPage.class, tabsModel).setIconType(GlyphIconType.picture));
				subMenu.add(new MenuBookmarkablePageLink<Void>(SigninPage.class, signInModel)
					.setIconType(GlyphIconType.lock));
				subMenu.add(new MenuBookmarkablePageLink<Void>(SignupPage.class, signUpModel)
					.setIconType(GlyphIconType.zoomin));
				subMenu.add(new MenuBookmarkablePageLink<Void>(PdfDownloadPage.class,
					downloadFileModel).setIconType(GlyphIconType.download));
				subMenu.add(new MenuBookmarkablePageLink<Void>(ReCaptchaPage.class,
					recaptchaModel).setIconType(GlyphIconType.check));
				subMenu.add(new MenuBookmarkablePageLink<Void>(SslReCaptchaPage.class,
					sslRecaptchaModel).setIconType(GlyphIconType.bullhorn));
				subMenu.add(new MenuBookmarkablePageLink<Void>(WicketUrlPage.class,
					wicketUrlsModel).setIconType(GlyphIconType.file));
				subMenu.add(new MenuBookmarkablePageLink<Void>(AlertsPage.class, alertsModel)
					.setIconType(GlyphIconType.bell));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					LabeledComponentsPage.class, labeledModel).setIconType(GlyphIconType.leaf));
				subMenu.add(new MenuBookmarkablePageLink<Void>(ButtonsPage.class,
					buttonsModel).setIconType(GlyphIconType.book));
				subMenu.add(new MenuBookmarkablePageLink<Void>(CheckboxesPage.class,
					checkboxesModel).setIconType(GlyphIconType.book));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					RadioComponentsExamplePage.class, radiosModel).setIconType(GlyphIconType.book));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					DeregistrationPage.class, deregistrationModel).setIconType(GlyphIconType.book));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					NotificationExamplesPage.class, toastrExampleModel)
					.setIconType(GlyphIconType.barcode));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					SocialNetworksExamplePage.class, socialNetExampleModel)
					.setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					ResourceLoadingExamplesPage.class, resourceLoadingExampleModel)
					.setIconType(GlyphIconType.pencil));
				subMenu.add(new MenuBookmarkablePageLink<Void>(
					TooltipsExamplePage.class, tooltipsExampleModel)
					.setIconType(GlyphIconType.bancircle));
				subMenu.add(new MenuBookmarkablePageLink<Void>(ExceptionPage.class,
					exceptionModel).setIconType(GlyphIconType.fire));

				return subMenu;
			}
		}.setIconType(GlyphIconType.folderopen);
	}

	/**
	 * Factory method for creating a new dropdown navbar item for the legal issues like term of use.
	 *
	 * @return the component
	 */
	protected Component newLegalDropDownButton()
	{
		final IModel<String> legacyMainModel = ResourceModelFactory.newResourceModel(
			"global.menu.legacy.label", this);
		final IModel<String> imprintModel = ResourceModelFactory.newResourceModel(
			"global.menu.imprint.label", this);
		final IModel<String> termOfUseModel = ResourceModelFactory.newResourceModel(
			"global.menu.termofuse.label", this);
		final IModel<String> copyrightModel = ResourceModelFactory.newResourceModel(
			"global.menu.copyright.label", this);
		return new NavbarDropDownButton(legacyMainModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId)
			{
				final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
				subMenu.add(new MenuBookmarkablePageLink<ImprintPage>(ImprintPage.class,
					imprintModel).setIconType(GlyphIconType.eyeopen));
				subMenu.add(new MenuBookmarkablePageLink<TermOfUsePage>(TermOfUsePage.class,
					termOfUseModel).setIconType(GlyphIconType.picture));
				subMenu.add(new MenuBookmarkablePageLink<ImprintPage>(ImprintPage.class,
					copyrightModel).setIconType(GlyphIconType.lock));

				return subMenu;
			}
		}.setIconType(GlyphIconType.folderopen)
		// .setInverted(true)
		;
	}


	/**
	 * Factory method for creating a new dropdown navbar item for the bootstrap themes.
	 *
	 * @return the component
	 */
	protected DropDownButton newThemesNavbarDropDownButton()
	{
		final DropDownButton dropdown = new NavbarDropDownButton(Model.of("Themes"))
		{
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isActive(final Component item)
			{
				return false;
			}

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId)
			{
				final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
				subMenu.add(new MenuHeader(Model.of("all available themes:")));
				subMenu.add(new MenuDivider());

				final IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
				final List<ITheme> themes = settings.getThemeProvider().available();

				for (final ITheme theme : themes)
				{
					final PageParameters params = new PageParameters();
					params.set("theme", theme.name());

					subMenu.add(new MenuBookmarkablePageLink<Page>(getPageClass(), params, Model
						.of(theme.name())));
				}
				return subMenu;
			}
		}.setIconType(GlyphIconType.book);
		return dropdown;
	}

}
