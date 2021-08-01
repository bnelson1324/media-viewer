package gui.components.tabs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import gui.GUIManager;
import gui.components.ConfirmationWindow;
import gui.components.TextBoxFileLocation;
import gui.components.media_display.MediaDisplayPanel;

public class TabModifyTags extends Tab {

	private TextBoxFileLocation tbFileLocation;
	private JPanel pnlSelectedMediaDisplay;
	
	private JTextField tfModName;
	private JTextField tfModDateCreated;
	private JTextField tfModDateAdded;
	private JTextField tfModAuthorName;
	private JTextField tfModAuthorLinks;
	private JTextArea tfModTags;
	
	private JButton btnSaveTags;
	
	
	public TabModifyTags(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		JLabel lblFileLocation = new JLabel("File Location: ");
		
		tbFileLocation = new TextBoxFileLocation();
		
		pnlSelectedMediaDisplay = new JPanel();
		pnlSelectedMediaDisplay.setLayout(new BorderLayout(0, 0));
		
		JLabel lblModName = new JLabel("Name: ");
		
		tfModName = new JTextField();
		tfModName.setColumns(10);
		
		JLabel lblModDateCreated = new JLabel("Date Created: ");
		
		tfModDateCreated = new JTextField();
		tfModDateCreated.setColumns(10);
		
		JLabel lblModDateAdded = new JLabel("Date Added: ");
		
		tfModDateAdded = new JTextField();
		tfModDateAdded.setColumns(10);
		
		JLabel lblModAuthorName = new JLabel("Author Name: ");
		
		tfModAuthorName = new JTextField();
		tfModAuthorName.setColumns(10);
		
		JLabel lblModAuthorLinks = new JLabel("Author Links: ");
		
		tfModAuthorLinks = new JTextField();
		tfModAuthorLinks.setColumns(10);
		
		JLabel lblModTags = new JLabel("Tags: ");
		
		tfModTags = new JTextArea();
		tfModTags.setRows(8);
		tfModTags.setColumns(10);
		tfModTags.setLineWrap(true);
		tfModTags.setWrapStyleWord(true);
		
		btnSaveTags = new JButton("Save Tags");
		btnSaveTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmationWindow cw = new ConfirmationWindow("Alert", "Are you sure you would like to save these tags?", "Yes", "No");
				cw.setOnChoice( () -> {
					if(cw.choice) {
						GUIManager.saveTags(tfModName.getText(), tfModDateCreated.getText(), tfModDateAdded.getText(), tfModAuthorName.getText(), tfModAuthorLinks.getText(), tfModTags.getText());
						updateTags();
					}
				});
			}
		});
		
		
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblModName, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tfModName, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblModDateCreated, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tfModDateCreated, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblModDateAdded, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tfModDateAdded, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblModAuthorName, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tfModAuthorName, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblModAuthorLinks, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tfModAuthorLinks, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblModTags, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tfModTags, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(btnSaveTags)
							.addGap(21))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblFileLocation))
								.addComponent(tbFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(btnSaveTags)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblModName))
								.addComponent(tfModName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblModDateCreated))
								.addComponent(tfModDateCreated, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblModDateAdded))
								.addComponent(tfModDateAdded, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblModAuthorName))
								.addComponent(tfModAuthorName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblModAuthorLinks))
								.addComponent(tfModAuthorLinks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblModTags))
								.addComponent(tfModTags, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(pnlSelectedMediaDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(93))
		);
		
		setLayout(groupLayout);

	}

	private void updateImage() {
		int imgWidth, imgHeight;
		imgWidth = this.getWidth() - 390;
		imgHeight = this.getHeight() - 80;
		pnlSelectedMediaDisplay.removeAll();
		MediaDisplayPanel mid = (MediaDisplayPanel) defaultValues.get("smiDisplay");
		if(mid == null) {
			return;
		}
		mid.setDisplaySize(imgWidth, imgHeight, true);
		pnlSelectedMediaDisplay.add(mid, BorderLayout.NORTH);
	}
	
	
	@SuppressWarnings("unchecked")
	private void updateTags() {
		tfModName.setText(GUIManager.unpackTagList((ArrayList<String>)(defaultValues.get("smiName")), ", "));
		tfModDateCreated.setText(GUIManager.unpackTagList((ArrayList<String>)(defaultValues.get("smiDateCreated")), ", "));
		tfModDateAdded.setText(GUIManager.unpackTagList((ArrayList<String>)(defaultValues.get("smiDateAdded")), ", "));
		tfModAuthorName.setText(GUIManager.unpackTagList((ArrayList<String>)(defaultValues.get("smiAuthorName")), ", "));
		tfModAuthorLinks.setText(GUIManager.unpackTagList((ArrayList<String>)(defaultValues.get("smiAuthorLinks")), ", "));
		tfModTags.setText(GUIManager.unpackTagList((ArrayList<String>)(defaultValues.get("smiTags")), ", "));
	}
	
	@Override
	public void updateTab() {
		// updates the text boxes and image
		tbFileLocation.setText(defaultValues.get("smi").toString());
		updateTags();
		updateImage();
	}

	@Override
	public void onSelect() {
		updateTab();
	}
	
	@Override
	public void onResize() {
		// updates image
		updateImage();
	}
}
