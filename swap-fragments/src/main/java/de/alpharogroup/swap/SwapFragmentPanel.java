package de.alpharogroup.swap;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;


/**
 * The abstract class SwapFragmentPanel holds to Fragment that can be swapped.
 *
 * @param <T>
 *            the generic type of the model object.
 */
public abstract class SwapFragmentPanel<T> extends GenericPanel<T>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	protected static final String FRAGMENT_ID = "fragmentsJoin";

	/** The view fragment. */
	protected Fragment view;

	/** The view fragment. */
	protected Fragment edit;

	/** The ModeContext that shows if the current panel is in the view mode or edit mode. */
	private ModeContext modeContext = ModeContext.VIEW_MODE;

	/**
	 * Instantiates a new swap fragment panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public SwapFragmentPanel(String id, IModel<T> model)
	{
		super(id, model);
		setModel(Args.notNull(model, "model"));
	}

	public ModeContext getModeContext()
	{
		return modeContext;
	}

	/**
	 * Abstract factory method for the edit fragment.
	 *
	 * @param id
	 *            the id
	 * @return the edit fragment
	 */
	protected abstract Fragment newFragmentEdit(final String id);


	/**
	 * Abstract factory method for the view fragment.
	 *
	 * @param id
	 *            the id
	 * @return the view fragment
	 */
	protected abstract Fragment newFragmentView(final String id);

	/**
	 * Swaps from the edit fragment to the view fragment.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected void onSwapFromEditToView(AjaxRequestTarget target, final Form<?> form)
	{
		target.add(edit);
		swapFragments();
		modeContext = ModeContext.VIEW_MODE;
	}

	/**
	 * Swaps from the view fragment to the edit fragment.
	 *
	 * @param target
	 *            the target
	 * @param form
	 *            the form
	 */
	protected void onSwapFromViewToEdit(AjaxRequestTarget target, final Form<?> form)
	{
		swapFragments();
		target.add(view);
		modeContext = ModeContext.EDIT_MODE;
	}

	/**
	 * Swap the fragments.
	 */
	protected void swapFragments()
	{
		Fragment fragment = view;
		view.replaceWith(edit);
		view = edit;
		edit = fragment;
	}

}
