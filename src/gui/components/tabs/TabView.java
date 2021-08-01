package gui.components.tabs;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import gui.components.TextBoxFileLocation;
import gui.components.media_display.MediaDisplayPanel;

public class TabView extends Tab {

	private TextBoxFileLocation tbFileLocation;
	private JPanel pnlSelectedMediaDisplay;
	
	public TabView(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		JLabel lblFileLocation = new JLabel("File Location: ");
		
		tbFileLocation = new TextBoxFileLocation();
		
		pnlSelectedMediaDisplay = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFileLocation, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tbFileLocation, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFileLocation))
						.addComponent(tbFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
					.addGap(9))
		);
		pnlSelectedMediaDisplay.setLayout(new BorderLayout(0, 0));
		setLayout(groupLayout);

	}
	
	// updates the selected media item's display panel
	private void updateDisplayPanel() {
		int pnlWidth, pnlHeight;
		pnlWidth = this.getWidth() - 32;
		pnlHeight = this.getHeight() - 64;
		pnlSelectedMediaDisplay.removeAll();
		MediaDisplayPanel mdp = (MediaDisplayPanel) defaultValues.get("smiDisplay");
		if(mdp == null) {
			return;
		}
		mdp.setDisplaySize(pnlWidth, pnlHeight, true);
		
		pnlSelectedMediaDisplay.add(mdp, BorderLayout.NORTH);
	}
	
	@Override
	public void updateTab() {
		// updates the text box and image
		tbFileLocation.setText(defaultValues.get("smi").toString());
		updateDisplayPanel();
	}


	@Override
	public void onSelect() {
		updateTab();
	}
	
	@Override
	public void onResize() {
		// updates image
		updateDisplayPanel();
	}
}
