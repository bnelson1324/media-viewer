package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.components.tabs.Tab;
import gui.components.tabs.TabHelp;
import gui.components.tabs.TabModifyTags;
import gui.components.tabs.TabSearch;
import gui.components.tabs.TabSettings;
import gui.components.tabs.TabView;

public class GraphicsFrame extends JFrame {

	private JPanel contentPane;
	
	private JTabbedPane tabbedPane;

	// currently selected tab
	private Tab selectedTab;
	
	/* Main JFrame for the app */

	public GraphicsFrame(HashMap<String, Object> defaultValues) {
		super("Media Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(960, 720));
		setBounds(100, 100, this.getMinimumSize().width, this.getMinimumSize().height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		TabSearch tabSearch = new TabSearch(defaultValues);
		tabbedPane.addTab("Search", null, tabSearch, null);
		
		TabView tabView = new TabView(defaultValues);
		tabbedPane.addTab("View", null, tabView, null);
		
		TabModifyTags tabModifyTags = new TabModifyTags(defaultValues);
		tabbedPane.addTab("Modify Tags", null, tabModifyTags, null);
		
		TabSettings tabSettings = new TabSettings(defaultValues);
		tabbedPane.addTab("Settings", null, tabSettings, null);
		
		TabHelp tabHelp = new TabHelp(defaultValues);
		tabbedPane.addTab("Help", null, tabHelp, null);
	}
	
	// adds listeners to the components
	public void addListeners() {
		// updates currently selected tab
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				selectedTab = (Tab) tabbedPane.getComponents()[tabbedPane.getSelectedIndex()];
				SwingUtilities.invokeLater( () -> {
		    		selectedTab.onSelect();
		    	});
			}
		});
		
		tabbedPane.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	selectedTab = (Tab) tabbedPane.getComponents()[tabbedPane.getSelectedIndex()];
		    	SwingUtilities.invokeLater( () -> {
		    		selectedTab.onResize();
		    	});
		    	
		    }
		});
	}

	public static GraphicsFrame init(HashMap<String, Object> defaultValues) {
		GraphicsFrame frame = new GraphicsFrame(defaultValues);
		frame.setVisible(true);
		frame.addListeners();
		return frame;
	}
	
	public Tab getSelectedTab() {
		return selectedTab;
	}
	
	public void setSelectedTabIndex(int n) {
		tabbedPane.setSelectedIndex(n);
	}
	
	public Tab getTabAtIndex(int n) {
		return (Tab) (tabbedPane.getComponentAt(n));
		
	}
	
}
