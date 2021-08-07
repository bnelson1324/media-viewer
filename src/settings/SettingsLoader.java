package settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SettingsLoader {

	private static BufferedReader br;
	
	private static HashMap<String, String> loaderSettingsMap;
	

	// loads the Media Viewer's settings
	public static void loadSettings() {
		// makes sure the settings directory exists
		File settingsDirectory = new File("settings");
		if(!settingsDirectory.exists()) {
			settingsDirectory.mkdir();
		}
		
		File settingsFile = new File("settings/settings.cfg");

		// makes sure settings file exists
		if(!settingsFile.exists()) {
			SettingsSaver.copyDefaultSettings(settingsFile);
		} 
		
		// reading file
		try {
			 br = new BufferedReader(new FileReader(settingsFile));
			 String line;
			 while ((line = br.readLine()) != null) {
				// puts settings in settingsMap
				String[] currentSetting = line.split("=");
				loaderSettingsMap.put(currentSetting[0], currentSetting[1]);
			 }
			 br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void init() {
		loaderSettingsMap = new HashMap<String, String>();
		loadSettings();
	}
	
	static HashMap<String, String> getSettingsMap() {
		return loaderSettingsMap;
	}
}
