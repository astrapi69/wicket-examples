package de.alpharogroup.wicket.dropdownchoices.panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import lombok.Getter;
import lombok.Setter;

public class DoubleDropDownPanelTest<T> extends FormComponentPanel
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ROOT_CHOICE_ID. */
	public static final String ROOT_CHOICE_ID = "rootChoice";

	/** The Constant CHILD_CHOICE_ID. */
	public static final String CHILD_CHOICE_ID = "childChoice";

	/** The root choice. */
	@Getter
	private DropDownChoice<T> rootChoice;

	/** The child choice. */
	@Getter
	private DropDownChoice<T> childChoice;

	/** The root renderer. */
	@Getter
	private final IChoiceRenderer<T> rootRenderer;

	/** The child renderer. */
	@Getter
	private final IChoiceRenderer<T> childRenderer;


	/** The selected root option. */
	@Getter
	@Setter
	private T selectedRootOption;

	/** The selected child option. */
	@Getter
	@Setter
	private T selectedChildOption;

	/** The models map. */
	@Getter
	@Setter
	private Map<T, List<T>> modelsMap = new HashMap<>();

	/** The root choices. */
	@Getter
	private final List<T> rootChoices;

	/** The child choices. */
	@SuppressWarnings("unused")
	private List<T> childChoices;

	public DoubleDropDownPanelTest(String id, final Map<T, List<T>> modelsMap, final T selectedRootOption,
		final IChoiceRenderer<T> rootRenderer, final IChoiceRenderer<T> childRenderer)
	{
		super(id);
		this.modelsMap = Args.notNull(modelsMap, "modelsMap");

		this.rootChoices = new ArrayList<>(this.modelsMap.keySet());

		this.selectedRootOption = Args.notNull(selectedRootOption, "selectedRootOption");

		this.childChoices = getChildChoices();
		this.rootRenderer = Args.notNull(rootRenderer, "rootRenderer");
		this.childRenderer = Args.notNull(childRenderer, "childRenderer");
	}

	/**
	 * Gets the child choices.
	 *
	 * @return the child choices
	 */
	public List<T> getChildChoices()
	{
		final List<T> childChoices = getModelsMap().get(getSelectedRootOption());
		if(ListExtensions.isEmpty(childChoices))
		{
			return Collections.emptyList();
		}
		return childChoices;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(rootChoice = newRootChoice(ROOT_CHOICE_ID));
		add(childChoice = newChildChoice(CHILD_CHOICE_ID));
	}

	/**
	 * Factory method for creating the new root {@link DropDownChoice}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new root {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new root {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newRootChoice(final String id)
	{
		final IModel<T> selectedRootOptionModel = PropertyModel.of(this, "selectedRootOption");
		final IModel<List<T>> rootChoicesModel = PropertyModel.of(this, "rootChoices");

		final DropDownChoice<T> rc = new LocalisedDropDownChoice<>(id, selectedRootOptionModel,
			rootChoicesModel, this.rootRenderer);
		rc.add(new AjaxFormComponentUpdatingBehavior("change")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				DoubleDropDownPanelTest.this.onRootChoiceUpdate(target);
			}

			@Override
			protected void onError(AjaxRequestTarget target, RuntimeException e)
			{
				DoubleDropDownPanelTest.this.onRootChoiceError(target, e);
			}
		});
		return rc;
	}

	/**
	 * Factory method for creating the new child {@link DropDownChoice}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new child {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new child {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newChildChoice(final String id)
	{
		final IModel<T> selectedChildOptionModel = new PropertyModel<>(this,
			"selectedChildOption");
		final IModel<List<T>> childChoicesModel = PropertyModel.of(this, "childChoices");
		final DropDownChoice<T> cc = new LocalisedDropDownChoice<>(id, selectedChildOptionModel,
			childChoicesModel, this.childRenderer);
		cc.setOutputMarkupId(true);
		cc.add(new AjaxFormComponentUpdatingBehavior("change")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				DoubleDropDownPanelTest.this.onChildChoiceUpdate(target);
			}

			@Override
			protected void onError(AjaxRequestTarget target, RuntimeException e)
			{
				DoubleDropDownPanelTest.this.onChildChoiceError(target, e);
			}
		});
		return cc;
	}

	protected void onRootChoiceUpdate(final AjaxRequestTarget target)
	{
		DoubleDropDownPanelTest.this.childChoice.modelChanging();
		target.add(DoubleDropDownPanelTest.this.childChoice);
		DoubleDropDownPanelTest.this.childChoice.modelChanged();
	}

	protected void onRootChoiceError(AjaxRequestTarget target, RuntimeException e)
	{
		System.err.println("onRootChoiceError:");
	}

	protected void onChildChoiceUpdate(final AjaxRequestTarget target)
	{
		target.add(DoubleDropDownPanelTest.this.childChoice);
	}

	protected void onChildChoiceError(AjaxRequestTarget target, RuntimeException e)
	{
		System.err.println("onChildChoiceError:");
	}

	protected void onRootSelectionChanged(Object newSelection)
	{
		T t = rootChoice.getModelObject();
		System.err.println("onRootSelectionChanged:"+newSelection);
	}

	protected void onChildSelectionChanged(Object newSelection)
	{
		System.err.println("onChildSelectionChanged:"+newSelection);
	}
}
