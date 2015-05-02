package de.alpharogroup.jquery.behaviors.tooltipster;

import java.util.HashMap;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;
import org.apache.wicket.util.template.TextTemplate;

import de.alpharogroup.WicketApplication;

/**
 * Mootip behavior, implements integration with this
 * http://www.uhleeka.com/dev/mootips/
 * 
 * The component you add this behavior to will be the component which the
 * tooltip appears for
 * 
 * @author nino.martinez @ jayway.dk
 */
public class TooltipsterBehaviour extends Behavior {
	private static final long serialVersionUID = 1L;

	private final TextTemplate mooTipTemplate = new PackageTextTemplate(
			TooltipsterBehaviour.class, "TooltipsterBehaviour.js");

	private TooltipsterSettings mootipSettings = TooltipsterSettings.builder()
			.build();

	/** The target component. */
	private Component component;

	private boolean addTitle = false;

	private String content = "";
	private String title = "";

	private boolean ajax = false;

	private boolean contributeCSS = true;



	/**
	 * simple tooltip, using title as tool tip it uses this syntax for splitting
	 * <code> title='this will be title:this will be content'</code>
	 * 
	 * @param title
	 * @param content
	 */
	public TooltipsterBehaviour(final String title, final String content) {
		addTitle = true;
		this.content = content;
		this.title = title;
	}

	/**
	 * simple tooltip, using title as tool tip it uses this syntax for splitting
	 * <code> title='this will be title:this will be content'</code>
	 * 
	 * @param title
	 * @param content
	 */
	public TooltipsterBehaviour(final String title, final String content,
			final boolean contribute) {
		this(title, content);
		contributeCSS = contribute;
	}


	private String generateJS(final TextTemplate textTemplate) {
		final HashMap<String, Object> variables = new HashMap<String, Object>();
		final String widgetId = getEscapedComponentMarkupId();
		variables.put("id", widgetId);
		variables
				.put("widgetId", ".toolTipImg" + getEscapedComponentMarkupId());
		variables.put("animation", mootipSettings.getAnimation());
		variables.put("arrow", mootipSettings.isArrow());
		if(mootipSettings.getArrowColor() != null && !mootipSettings.getArrowColor().isEmpty()) {
			variables.put("arrowColor", mootipSettings.getArrowColor());
		}
		variables.put("autoClose", mootipSettings.isAutoClose());
		variables.put("content", mootipSettings.getContent());
		variables.put("contentAsHTML", mootipSettings.isContentAsHTML());
		variables.put("contentCloning", mootipSettings.isContentCloning());
		variables.put("debug", mootipSettings.isDebug());
		variables.put("delay", mootipSettings.getDelay());
		variables.put("minWidth", mootipSettings.getMinWidth());
		variables.put("maxWidth", mootipSettings.getMaxWidth());
		variables.put("functionInit", mootipSettings.getFunctionInit());
		variables.put("functionBefore", mootipSettings.getFunctionBefore());
		variables.put("functionReady", mootipSettings.getFunctionReady());
		variables.put("functionAfter", mootipSettings.getFunctionAfter());
		variables.put("hideOnClick", mootipSettings.isHideOnClick());
		variables.put("icon", mootipSettings.getIcon());
		variables.put("iconCloning", mootipSettings.isIconCloning());
		variables.put("iconDesktop", mootipSettings.isIconDesktop());
		variables.put("iconTheme", mootipSettings.getIconTheme());
		variables.put("iconTouch", mootipSettings.isIconTouch());
		variables.put("interactive", mootipSettings.isInteractive());
		variables.put("interactiveTolerance",
				mootipSettings.getInteractiveTolerance());
		variables.put("multiple", mootipSettings.isMultiple());
		variables.put("offsetX", Math.round(mootipSettings.getOffsetX()));
		variables.put("offsetY", Math.round(mootipSettings.getOffsetY()));
		variables.put("onlyOne", mootipSettings.isOnlyOne());
		variables.put("position", mootipSettings.getPosition());
		variables.put("positionTracker", mootipSettings.isPositionTracker());
		variables.put("positionTrackerCallback",
				mootipSettings.getPositionTrackerCallback());
		variables.put("restoration", mootipSettings.getRestoration());
		variables.put("speed", mootipSettings.getSpeed());
		variables.put("timer", mootipSettings.getTimer());
		variables.put("theme", mootipSettings.getTheme());
		variables.put("touchDevices", mootipSettings.isTouchDevices());
		variables.put("trigger", mootipSettings.getTrigger());
		variables.put("updateAnimation", mootipSettings.isUpdateAnimation());

		textTemplate.interpolate(variables);
		return textTemplate.asString();
	}

	/**
	 * Gets the escaped DOM id that the input will get attached to. All non word
	 * characters (\W) will be removed from the string.
	 * 
	 * @return The DOM id of the input - same as the component's markup id}
	 */
	protected final String getEscapedComponentMarkupId() {
		return component.getMarkupId().replaceAll("\\W", "");
	}

	public boolean isAjax() {
		return ajax;
	}

	/**
	 * Add the required css and js files to the page
	 * 
	 * Also add the javascript to create the tooltip
	 */
	@Override
	public void renderHead(Component c, final IHeaderResponse response)
	{ 
		response.render(JavaScriptHeaderItem.forReference(
				WicketApplication.get().getJavaScriptLibrarySettings().getJQueryReference()));
		
		response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(
			TooltipsterBehaviour.class, "jquery.tooltipster.js")));
		
		response.render(OnLoadHeaderItem.forScript(generateJS(mooTipTemplate)));
	}

	public void setMootipSettings(final TooltipsterSettings mootipSettings) {
		this.mootipSettings = mootipSettings;
	}
}
