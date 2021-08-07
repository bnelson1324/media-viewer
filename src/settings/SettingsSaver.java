package settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class SettingsSaver {

	/* This is what saves settings to settings/settings.cfg */ 
	
	public static void saveSettings() {
		String toWrite = "";
		
		// gets each setting on a new line
		for(Map.Entry<String, String> entry : SettingsHandler.getSettingsMap().entrySet()) {
			toWrite += entry.getKey();
			toWrite += "=";
			toWrite += entry.getValue();
			toWrite += "\n";
		}
		
		// overwrite the settings file
		try {
			FileWriter fw = new FileWriter("settings/settings.cfg");
			fw.write(toWrite);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// copies the default settings file to the actual settings file
	public static void copyDefaultSettings(File actualSettingsFile) {
		// create a new settings file by copying the default one if it doesn't exist
		File defaultSettings = new File("res/DEFAULT_SETTINGS.cfg");
		try {
			Files.copy(defaultSettings.toPath(), actualSettingsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Settings reset to default");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
