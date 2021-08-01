package gui.components.media_display;

import java.nio.file.Path;

public class VideoDisplayPanelPreview extends VideoDisplayPanel {

	protected VideoDisplayPanelPreview(Path mediaItem, boolean itemInStorageFolder) {
		super(mediaItem, itemInStorageFolder);
		this.setDisplaySize(256, 256, false);
	}
}
