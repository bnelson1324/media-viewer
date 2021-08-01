package gui.components.media_display;

import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import javafx.animation.PauseTransition;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import media_control.MediaHandler;

public class VideoDisplayPanel extends ImageDisplayPanel {

	protected MediaPlayer mp;
	protected MediaView mv;
	
	protected VideoDisplayPanel(Path mediaItem, boolean itemInStorageFolder) {
		super(mediaItem, itemInStorageFolder, false);
		
		// creates jfxpanel to initialize jfx toolkit
		new JFXPanel();	
			prepareMedia();
			mp.setOnReady( () -> {
				prepareSnapshot();
			});
	}
	
	protected void prepareMedia() {
		Media fxMedia = new Media(MediaHandler.getFullRelativePath(mediaItem, itemInStorageFolder).toFile().toURI().toString());
		mp = new MediaPlayer(fxMedia);
		mv = new MediaView(mp);
	}
	
	// prepares thumbnail of video
	protected void prepareSnapshot() {
		// sets media player to the middle of the media
		Duration middleTime = mp.getMedia().getDuration().divide(2);
		mp.seek(middleTime);
		
	
		// delay to let mv prepare for snapshot (i think)
		PauseTransition pt = new PauseTransition(new Duration(1000));
		pt.setOnFinished( (e) -> {
			WritableImage wi = new WritableImage(mp.getMedia().getWidth(), mp.getMedia().getHeight());
			mv.snapshot(new SnapshotParameters(), wi);
			
			mediaItemImage = SwingFXUtils.fromFXImage(wi, null);
			imageLabel.setIcon(new ImageIcon(mediaItemImage));

			this.setDisplaySize(currentWidth, currentHeight, currentKeepAspectRatio);
			
			readyToRender = true;
		});
		pt.play();
	}
	
	@Override
	public void setDisplaySize(int width, int height, boolean keepAspectRatio) {
		SwingUtilities.invokeLater( () -> {
			super.setDisplaySize(width, height, keepAspectRatio);
		});	
	}
	
	@Override
	protected void createCopyItem() {
		copyItem = null;
	}
	
	@Override
	protected void createContextMenuChoices() {
		contextMenu.addChoiceOpenFile();
		contextMenu.addChoiceOpenFileLoc();
	}
	
}
