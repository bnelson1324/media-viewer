package gui.components.media_display;

import java.awt.BorderLayout;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.components.context_menu.MediaItemContextMenu;

public abstract class MediaDisplayPanel extends JPanel {
	
	/* JPanel displaying a media item */
	
	protected Path mediaItem;
	
	// determines whether program should read relative to the root storage folder, or to the working directory
	protected boolean itemInStorageFolder;
	
	// read only values for width and height, and keepAspectRatio
	protected int currentWidth, currentHeight;
	protected boolean currentKeepAspectRatio;
	
	protected MediaItemContextMenu contextMenu;
		
	// what is copied when selecting copy from the context menu
	protected Transferable copyItem;
	
	protected MediaDisplayPanel(Path mediaItem, boolean itemInStorageFolder) {
		this.mediaItem = mediaItem;
		this.itemInStorageFolder = itemInStorageFolder;
		this.setLayout(new BorderLayout());
		this.addContextMenu();
	}
	
	public Path getMediaItem() {
		return mediaItem;
	}

	
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		currentWidth = width;
		currentHeight = height;
		currentKeepAspectRatio = keepAspectRatio;
	}
	
	private void addContextMenu() {
		createCopyItem();
		contextMenu = new MediaItemContextMenu(mediaItem, itemInStorageFolder, copyItem);
		this.add(contextMenu);
		
		createContextMenuChoices();
	}
	
	// creates copy item
	protected abstract void createCopyItem();
	
	// creates choices for the context menu and copyItem
	protected abstract void createContextMenuChoices();
	
	// gets component displaying the media
	public abstract JComponent getDisplayComponent();

	public static MediaDisplayPanel makeMediaDisplayPanel(Path mediaItem, boolean itemInStorageFolder, boolean preview) {
		String fileType;
		
		try {
			fileType = Files.probeContentType(mediaItem).split("/")[0];
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		MediaDisplayPanel mdpToReturn;
		
		switch(fileType) {
			default:
				System.out.println("unknown: " + mediaItem);
				mdpToReturn = new UnknownDisplayPanel(mediaItem, itemInStorageFolder);
				break;
			case "image":
				mdpToReturn = new ImageDisplayPanel(mediaItem, itemInStorageFolder, true);
				break;
			case "video":
				if(!preview) {
					mdpToReturn = new VideoDisplayPanel(mediaItem, itemInStorageFolder);
				} else {
					mdpToReturn = new VideoDisplayPanelPreview(mediaItem, itemInStorageFolder);
				}
				break;
			case "audio":
				mdpToReturn = new AudioDisplayPanel(mediaItem, itemInStorageFolder);
				break;
			case "text":
				if(!preview) {
					mdpToReturn = new TextDisplayPanel(mediaItem, itemInStorageFolder, true);
				} else {
					mdpToReturn = new TextDisplayPanel(mediaItem, itemInStorageFolder, false);
				}
				break;
		}
		// context menu listeners
		if(mdpToReturn.contextMenu != null) {
			mdpToReturn.getDisplayComponent().addMouseListener(mdpToReturn.contextMenu.contextMenuOpener);
		}
		
		return mdpToReturn;
	}	

	
}
