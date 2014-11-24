package de.alpharogroup.popupoverlay;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.core.Options;

public class PopupoverlayBehavior  extends JQueryBehavior {

	public PopupoverlayBehavior(String selector, String method, Options options) {
		super(selector, method, options);

        this.add(new JavaScriptResourceReference(PopupoverlayBehavior.class,
                                "jquery.popupoverlay.js"));
	}

}
