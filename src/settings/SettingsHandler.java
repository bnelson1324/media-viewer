package settings;

import java.util.HashMap;

public class SettingsHandler {

	private static HashMap<String, String> settingsMap;
	
	
	public static String getSetting(String settingName) {
		return (String) settingsMap.get(settingName);
	}
	
	public static void modifySetting(String settingName, String newValue) {
		settingsMap.put(settingName, newValue);
	}
	
	public static void init() {
		SettingsLoader.init();
		
		settingsMap = SettingsLoader.getSettingsMap();
	}
	
	public static HashMap<String, String> getSettingsMap() {
		return settingsMap;
	}
}
