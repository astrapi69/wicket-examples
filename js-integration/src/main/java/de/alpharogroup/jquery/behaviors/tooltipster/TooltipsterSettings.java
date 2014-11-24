package de.alpharogroup.jquery.behaviors.tooltipster;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class TooltipsterSettings implements Serializable {
	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Determines how the tooltip will animate in and out. Feel free to modify
	 * or create custom transitions in the tooltipster.css file. In IE9 and 8,
	 * all animations default to a JavaScript generated, fade animation.
	 * Default: 'fade'
	 * Posible values: 
	 * fade, grow, swing, slide, fall
	 */
	private String animation = "fade";
	/**
	 * Adds the "speech bubble arrow" to the tooltip. Default: true
	 */
	private boolean arrow = true;

	/**
	 * Select a specific color for the "speech bubble arrow". Default: will inherit the tooltip's background color.
	 * Posible values: 
	 * hex code / rgb
	 */
	private String arrowColor;

	/**
	 * If autoClose is set to false, the tooltip will never close unless you call the 'hide' method yourself. Default: true
	 */
	private boolean autoClose = true;

	/**
	 * 
	 */
	private String content;

	/**
	 * 
	 */
	private boolean contentAsHTML;

	/**
	 * 
	 */
	private boolean contentCloning;

	/**
	 * 
	 */
	private boolean debug;

	/**
	 * 
	 */
	private int delay;

	/**
	 * 
	 */
	private int minWidth;

	/**
	 * 
	 */
	private int maxWidth;

	/**
	 * 
	 */
	private String functionInit;

	/**
	 * 
	 */
	private String functionBefore;

	/**
	 * 
	 */
	private String functionReady;

	/**
	 * 
	 */
	private String functionAfter;

	/**
	 * 
	 */
	private boolean hideOnClick;

	/**
	 * 
	 */
	private String icon;

	/**
	 * 
	 */
	private boolean iconCloning;

	/**
	 * 
	 */
	private boolean iconDesktop;

	/**
	 * 
	 */
	private String iconTheme;

	/**
	 * 
	 */
	private boolean iconTouch;

	/**
	 * 
	 */
	private boolean interactive;

	/**
	 * 
	 */
	private int interactiveTolerance;

	/**
	 * 
	 */
	private boolean multiple;

	/**
	 * 
	 */
	private int offsetX;

	/**
	 * 
	 */
	private int offsetY;

	/**
	 * 
	 */
	private boolean onlyOne;

	/**
	 * 
	 */
	private String position;

	/**
	 * 
	 */
	private boolean positionTracker;

	/**
	 * 
	 */
	private String positionTrackerCallback;

	/**
	 * 
	 */
	private String restoration;

	/**
	 * 
	 */
	private int speed;

	/**
	 * 
	 */
	private int timer;

	/**
	 * 
	 */
	private String theme;

	/**
	 * 
	 */
	private boolean touchDevices;

	/**
	 * 
	 */
	private String trigger;

	/**
	 * 
	 */
	private boolean updateAnimation;

}
