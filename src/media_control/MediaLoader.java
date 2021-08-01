package media_control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import media.MediaData;
import settings.SettingsHandler;

public class MediaLoader {

	/* This is what loads media items and data */
	
	private static Reader myReader;
	static Gson gson;
	
	static JsonArray mediaDataStorageJson;

	
	// all media items in the storage folder
	private static ArrayList<Path> loaderAllMediaItems;
	
	// pairs file path with a media object
	private static HashMap<Path, MediaData> loaderAllMediaData;
	
	private static void loadMediaItems() {
		// finds every file from the root storage folder
		
		Path rootStorageFolder = Paths.get(SettingsHandler.getSetting("rootStorageFolderLoc"));
		ArrayList<Path> allFiles = new ArrayList<Path>();
		fetchFiles(rootStorageFolder.toFile(), allFiles);
		
		for(Path f : allFiles) {
			// removes root folder storage location from the path
			Path relativePath = Paths.get(f.toString().substring(rootStorageFolder.toString().length() + 1));
			loaderAllMediaItems.add(relativePath);
		}
	}

	@SuppressWarnings("unchecked")
	private static void loadMediaData() {
		gson = new GsonBuilder()
			.setPrettyPrinting()
			.create();
		
		// gets mediaStorage json file
		try {
			myReader = new FileReader(new File("mediaDataStorage.json"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		mediaDataStorageJson = gson.fromJson(myReader, JsonArray.class);
		
		// adds all data from mediaDataStorage to loaderAllMediaData
		for(JsonElement mediaDataJsonElement : mediaDataStorageJson) {
			JsonObject mdJson = (JsonObject) mediaDataJsonElement;
	
			String mdPath = mdJson.get("fileLocation").getAsString();  
			ArrayList<String> mdName = gson.fromJson(mdJson.get("name"), ArrayList.class);
			ArrayList<String> mdDateCreated = gson.fromJson(mdJson.get("dateCreated"), ArrayList.class);
			ArrayList<String> mdDateAdded = gson.fromJson(mdJson.get("dateAdded"), ArrayList.class);
			ArrayList<String> mdAuthorName = gson.fromJson(mdJson.get("authorName"), ArrayList.class);
			ArrayList<String> mdAuthorLink = gson.fromJson(mdJson.get("authorLinks"), ArrayList.class);
			ArrayList<String> mdTags = gson.fromJson(mdJson.get("tags"), ArrayList.class);
			
			MediaData md = new MediaData(Paths.get(mdPath), mdName, mdDateCreated, mdDateAdded, mdAuthorName, mdAuthorLink, mdTags);
			loaderAllMediaData.put(Paths.get(mdPath), md);
		}
		try {
			myReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	// recursively adds all non-directory files from a directory, including from its subdirectories, into an ArrayList
	private static void fetchFiles(File dir, ArrayList<Path> allNonDirFiles) {
		File[] allFiles = dir.listFiles();
	
		for(File f : allFiles) {
			if(f.isDirectory()) {
				fetchFiles(f, allNonDirFiles);
			} else {
				allNonDirFiles.add(f.toPath());
			}
		}
	}

	// adds 'untagged' tag to mediaItems without tag
	private static void addUntaggedTag() {
		ArrayList<Path> untaggedMediaItems = new ArrayList<Path>();
		
		// finds all untagged media items
		for(Path p : loaderAllMediaItems) {
			if(loaderAllMediaData.get(p) == null) {
				untaggedMediaItems.add(p);
			}
		}
		
		// adds 'untagged' tag to them
		for(Path p : untaggedMediaItems) {
			ArrayList<String> tags = new ArrayList<String>();
			tags.add("untagged");
			MediaData md = new MediaData(p, new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), tags);
			loaderAllMediaData.put(p, md);
		}
	}
	
	static ArrayList<Path> getMediaItems() {
		return loaderAllMediaItems;
	}
	
	static HashMap<Path, MediaData> getMediaData() {
		return loaderAllMediaData;
	}
	
	public static void init() {
		loaderAllMediaItems = new ArrayList<Path>();
		loaderAllMediaData = new HashMap<Path, MediaData>();
		loadMediaItems();
		loadMediaData();
		addUntaggedTag();
	}

	
	
	
	
}
