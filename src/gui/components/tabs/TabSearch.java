package gui.components.tabs;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.wordpress.tips4java.WrapLayout;

import gui.components.MediaGridSquare;
import media_control.MediaHandler;
import javax.swing.ScrollPaneConstants;

public class TabSearch extends Tab {

	private JTextField tfSearchBox;
	
	private JPanel mediaDisplayGrid;
	
	
	public TabSearch(HashMap<String, Object> defaultValues) {
		super(defaultValues);
		
		JLabel lblFileLocation = new JLabel("Search By Tag:");
		
		tfSearchBox = new JTextField();
		tfSearchBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					TabSearch.this.updateDisplayGrid();
					revalidate();
					repaint();
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFileLocation, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(tfSearchBox, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(tfSearchBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		mediaDisplayGrid = new JPanel();
		scrollPane.setViewportView(mediaDisplayGrid);
		mediaDisplayGrid.setLayout(new WrapLayout(FlowLayout.LEFT, 24, 16));
		setLayout(groupLayout);

	}

	
	private void updateDisplayGrid() {
		ArrayList<Path> results;
		if(!tfSearchBox.getText().equals("")) {
			defaultValues.put("currentSearch", tfSearchBox.getText());
			results = MediaHandler.getMediaItemsByTag(tfSearchBox.getText());
		} else {
			// if searching "", display every media item
			results = MediaHandler.getMediaItemsByTag("untagged || !untagged");
		}
		mediaDisplayGrid.removeAll();
		for(Path mi : results) {
			MediaGridSquare mgs = new MediaGridSquare(mi);
			mediaDisplayGrid.add(mgs);
		}
		mediaDisplayGrid.revalidate();
	}
	
	
	@Override
	public void updateTab() {
		tfSearchBox.setText(defaultValues.get("currentSearch").toString());
		updateDisplayGrid();
		repaint();
	}

	@Override
	public void onSelect() {
	}
	
	@Override
	public void onResize() {
	}
}
