package de.alpharogroup.popupoverlay;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class PopupoverlayPanel extends Panel {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private WebMarkupContainer overlayReference;

	public PopupoverlayPanel(String id, IModel<?> model) {
		super(id, model);
		
		Button openButton = new Button("openButton");
		add(openButton);
		
		add(overlayReference = newOverlayReference("overlayReference"));
		
		// add class attributte with the markup id from the overlay with the suffix '_open'
		// that indicates that the overlay shell open...
		openButton.add(new AttributeModifier("class", overlayReference.getMarkupId()+ "_open"));
		
		Button button = new Button("button");
		overlayReference.add(button);
		// add class attributte with the markup id from the overlay with the suffix '_close'
		// that indicates that the overlay shell close...
		button.add(new AttributeModifier("class", overlayReference.getMarkupId()+ "_close"));
	}
	
	protected WebMarkupContainer newOverlayReference(String id) {
		WebMarkupContainer or = new WebMarkupContainer(id);
		PopupoverlaySettings settings = new PopupoverlaySettings();
		settings.setBlur(false);
//		settings.setOnopen("function() {"
//				+ "    alert('Popup just opened!');"
//				+ "  }");
		or.add(new PopupoverlayBehavior(settings));
		or.setOutputMarkupId(true);
		return or;
	}

}
