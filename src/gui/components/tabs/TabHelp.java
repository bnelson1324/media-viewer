package gui.components.tabs;

import java.awt.BorderLayout;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.swing.border.EmptyBorder;

import gui.components.media_display.MediaDisplayPanel;

public class TabHelp extends Tab {


	public TabHelp(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		setLayout(new BorderLayout(0, 0));
		
		MediaDisplayPanel helpPanel = MediaDisplayPanel.makeMediaDisplayPanel(Paths.get("res/HELP.txt"), false, false);
		helpPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(helpPanel, BorderLayout.CENTER);
	}

	@Override
	public void updateTab() {
	}

	@Override
	public void onSelect() {
	}

	@Override
	public void onResize() {
	}
	
	
}
