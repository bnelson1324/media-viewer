package media_control;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import media.MediaData;

public class MediaSaver {

	/* This is what saves media data to mediaStorage.json */
	
	
	// saves media data to mediaDataStorage.json
	public static void saveMediaData() {
		// create array of jsonObjects
		ArrayList<JsonElement> allJsonMediaData = new ArrayList<JsonElement>();
		
		// create a json object representing each media data, add these to the array
		for(Map.Entry<Path, MediaData> entry : MediaHandler.getAllMediaData().entrySet()) {
			JsonObject mdJson = new JsonObject();
			
			// adds file location and media data to mdJson
			mdJson.addProperty("fileLocation", entry.getKey().toString());
			mdJson.add("name", MediaLoader.gson.toJsonTree(entry.getValue().getName()));
			mdJson.add("dateCreated", MediaLoader.gson.toJsonTree(entry.getValue().getDateCreated()));
			mdJson.add("dateAdded", MediaLoader.gson.toJsonTree(entry.getValue().getDateAdded()));
			mdJson.add("authorName", MediaLoader.gson.toJsonTree(entry.getValue().getAuthorName()));
			mdJson.add("authorLinks", MediaLoader.gson.toJsonTree(entry.getValue().getAuthorLinks()));
			mdJson.add("tags", MediaLoader.gson.toJsonTree(entry.getValue().getGenericTags()));
			
			allJsonMediaData.add(mdJson);
		}
		
		// overwrite mediaDataStorageJson with the array
		MediaLoader.mediaDataStorageJson = (JsonArray) MediaLoader.gson.toJsonTree(allJsonMediaData);
	
		// overwrite the mediaDataStorage file with mediaDataStorageJson
		try {
			FileWriter fw = new FileWriter("mediaDataStorage.json");
			fw.write(MediaLoader.gson.toJson(MediaLoader.mediaDataStorageJson));
			fw.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
}
