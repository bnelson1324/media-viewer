package gui.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JTextField;

import gui.GUIManager;

public class TextBoxFileLocation extends JTextField {
	
	/* Text box for inputting file location */
	
	public TextBoxFileLocation() {
		super();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					// changes selected media item and updates selected tab
					GUIManager.changeSelectedMediaItem(getFileLocation());
					GUIManager.updateSelectedTab();
					GUIManager.appFrame.getSelectedTab().revalidate();
					GUIManager.appFrame.getSelectedTab().repaint();
				}
			}
		});
	}
	
	public Path getFileLocation() {
		return Paths.get(this.getText());
	}
}
