package gui.components;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GUIManager;
import gui.components.media_display.MediaDisplayPanel;

public class MediaGridSquare extends JPanel {

	/* JPanel representing one grid square in the search tab. Displays a file name and an image */
	
	private JLabel nameLabel;
	private MediaDisplayPanel displayPanel;
	
	public MediaGridSquare(Path mi) {
		setLayout(new BorderLayout(0, 0));
		
		nameLabel = new JLabel(mi.getFileName().toString());
		nameLabel.setFont(new Font("Label.font", Font.PLAIN, 16));
		add(nameLabel, BorderLayout.NORTH);
		
		displayPanel = MediaDisplayPanel.makeMediaDisplayPanel(mi, true, true);
		add(displayPanel, BorderLayout.SOUTH);
		
		displayPanel.setDisplaySize(256, 256, false);
		
		displayPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		displayPanel.getDisplayComponent().addMouseListener(new MouseAdapter() {

			// detects if should open context menu
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					GUIManager.changeSelectedMediaItem(mi);
					GUIManager.setSelectedTabIndex(1);
					GUIManager.appFrame.getSelectedTab().updateTab();
				} 
			}
		});
		
	}
	
}
