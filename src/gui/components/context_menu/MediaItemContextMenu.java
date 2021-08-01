package gui.components.context_menu;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JPopupMenu;

import media_control.MediaHandler;

public class MediaItemContextMenu extends JPopupMenu {

	/* Context menu when right clicking a media item */

	private Path mediaItem;
	private boolean itemInStorageFolder;
	private Transferable copyItem;
	
	// mouse listener to open/close this context menu
	public MouseListener contextMenuOpener;
	
	public MediaItemContextMenu(Path mediaItem, boolean itemInStorageFolder, Transferable copyItem) {
		super("Context Menu");
		this.mediaItem = mediaItem;
		this.itemInStorageFolder = itemInStorageFolder;
		this.copyItem = copyItem;
		
		this.contextMenuOpener = new MouseAdapter() {
			
			// detects if should open context menu
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					MediaItemContextMenu.this.setVisible(true);
					MediaItemContextMenu.this.setLocation(e.getXOnScreen(), e.getYOnScreen());
				} 
			}
			
			// detects if should close context menu
			@Override public void mouseEntered(MouseEvent e) {
				if(!MediaItemContextMenu.this.contains(e.getPoint())) {
					MediaItemContextMenu.this.setVisible(false);
				}
			}
		};
	}
	
	// open file
	public void addChoiceOpenFile() {
		MediaItemContextMenuChoice cOpenFile = new MediaItemContextMenuChoice("Open file", this);
		cOpenFile.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {
            	try {
					Desktop.getDesktop().open((MediaHandler.getFullRelativePath(mediaItem, itemInStorageFolder).toFile()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}         
            }                 
         });
		this.add(cOpenFile);
	}
	
	// copy file
	public void addChoiceCopy() {
		MediaItemContextMenuChoice cCopy = new MediaItemContextMenuChoice("Copy", this);
		cCopy.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {
            	Toolkit tk = Toolkit.getDefaultToolkit();
            	Clipboard clipboard = tk.getSystemClipboard();
            	
            	// copies copyItem to clipboard
				clipboard.setContents(copyItem, null);
				System.out.println("Copied file to clipboard");         
            }                 
         });
		this.add(cCopy);
	}
	
	// open file location
	public void addChoiceOpenFileLoc() {
		MediaItemContextMenuChoice cOpenFileLoc = new MediaItemContextMenuChoice("Open file location", this);
		cOpenFileLoc.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {
            	try {
					Desktop.getDesktop().open((MediaHandler.getFullRelativeFileLocation(mediaItem, itemInStorageFolder).toFile()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }                 
         });
		this.add(cOpenFileLoc);
	}

}
