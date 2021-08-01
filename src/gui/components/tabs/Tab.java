package gui.components.tabs;

import java.util.HashMap;

import javax.swing.JPanel;

public abstract class Tab extends JPanel {

	/* Abstract class for tags in this app */
	
	// default values for certain fields in the gui
	protected HashMap<String, Object> defaultValues;
	
	public Tab(HashMap<String, Object> defaultValues) {
		this.defaultValues = defaultValues;
	}
	
	// updates the components on the tab
	public abstract void updateTab();
	
	// called by a specific tab when it is selected
	public abstract void onSelect();

	// called by a specific tab when the tabbed pane is resized and it is selected
	public abstract void onResize();

}
