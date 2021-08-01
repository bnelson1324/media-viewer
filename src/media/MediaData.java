package media;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import settings.SettingsHandler;


public class MediaData {

	/* This is data describing a media file */
	
	// explicit data
	private ArrayList<String> name, dateCreated, dateAdded, authorName, authorLinks, tags;
	
	// implicit data
	private String fileFormat, mediaType;
	
	private Path mediaItem;
	
	private ArrayList<String> allTags;
	
	// date format used to read user-inputted dates in tags
	private static SimpleDateFormat dateFormat;
	// date format used for implicit tags
	private static SimpleDateFormat tagDateFormat;
	
	
	public MediaData(Path mediaItem, ArrayList<String> name, ArrayList<String> dateCreated, ArrayList<String> dateAdded, ArrayList<String> authorName, ArrayList<String> authorLinks, ArrayList<String> tags) {
		this.mediaItem = mediaItem;
		this.name = name;
		this.dateCreated = dateCreated;
		this.dateAdded = dateAdded;
		this.authorName = authorName;
		this.authorLinks = authorLinks;
		this.tags = tags;
		
		createAllTags();
	}
	
	/* explicit data */
	public ArrayList<String> getName() {
		return name;
	}
	
	public ArrayList<String> getDateCreated() {
		return dateCreated;
	}
	
	public ArrayList<String> getDateAdded() {
		return dateAdded;
	}
	
	public ArrayList<String> getAuthorName() {
		return authorName;
	}
	
	public ArrayList<String> getAuthorLinks() {
		return authorLinks;
	}
	
	// gets the generic tags (tags ArrayList)
	public ArrayList<String> getGenericTags() {
		return tags;
	}
	
	/* implicit data */
	public String getFileFormat() {
		return fileFormat;
	}
	
	public String getMediaType() {
		return mediaType;
	}
	
	/* other */
	// gets all the tags, including the ones that aren't misc 
	public ArrayList<String> getAllTags() {
		return allTags;
	}
	
	// creates the arraylist allTags
	private void createAllTags() {
		// adds user-defined fields and tags
		allTags = new ArrayList<String>();
		for(String s : name) {
			allTags.add("name:" + s);
		}
		for(String s : dateCreated) {
			allTags.add("dateCreated:" + s);
		}
		for(String s : dateAdded) {
			allTags.add("dateAdded:" + s);
		}
		for(String s : authorName) {
			allTags.add("authorName:" + s);
		}
		for(String s : authorLinks) {
			allTags.add("authorLinks:" + s);
		}
		for(String s : tags) {
			allTags.add(s);
		}
		
		// adds implicit tags
		addTagFileFormat(allTags);
		addTagMediaType(allTags);
		addTagDates(allTags);
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "Name: '" + name;
		str += "', dateCreated: " + dateCreated;
		str += ", dateAdded: " + dateAdded;
		str += ", authorName: " + authorName;
		str += ", authorLinks: " + authorLinks;
		str += ", tags: " + tags;
		return str;
	}
	
	
	// creates a new date format using current settings
	public static void createDateFormat() {
		String dateFormatSetting = (SettingsHandler.getSetting("dateFormat")).toLowerCase();
		// formats the date format setting so that java can create the right SimpleDateFormat with it
		String dateFormatStr = "";
		for(int i = 0; i < dateFormatSetting.length(); i++) {
			char c = Character.toLowerCase(dateFormatSetting.charAt(i));
			if(c == 'm') {
				c = 'M';
			}
			dateFormatStr += c;
		}
		dateFormat = new SimpleDateFormat(dateFormatStr);
	}
	
	
	/* helper methods for creating implicit tags */
	
	// adds file format (mp3, txt, etc)
	private void addTagFileFormat(ArrayList<String> allTags) {
		String mediaStr = mediaItem.toString();
		fileFormat = mediaStr.substring(mediaStr.indexOf('.') + 1);
		allTags.add("fileFormat:" + fileFormat);
	}
	
	// adds media type (text, video, etc)
	private void addTagMediaType(ArrayList<String> allTags) {
		try {
			String fileProbe = Files.probeContentType(mediaItem);
			if(fileProbe != null) {
				mediaType = fileProbe.split("/")[0];
				allTags.add("mediaType:" + mediaType);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addTagDates(ArrayList<String> allTags) {
		for(String date : dateCreated) {
			try {
				Date userDate = dateFormat.parse(date);
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(userDate);

				allTags.add("yearCreated:" + calendar.get(Calendar.YEAR));
				allTags.add("monthCreated:" + tagDateFormat.format(calendar.getTime()).toLowerCase());
				allTags.add("dayCreated:" + calendar.get(Calendar.DAY_OF_MONTH));
			} catch (ParseException e) {
				System.out.println("Could not parse date: " + date);
			}
		}
		for(String date : dateAdded) {
			try {
				Date userDate = dateFormat.parse(date);
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(userDate);

				allTags.add("yearAdded:" + calendar.get(Calendar.YEAR));
				allTags.add("monthAdded:" + tagDateFormat.format(calendar.getTime()).toLowerCase());
				allTags.add("dayAdded:" + calendar.get(Calendar.DAY_OF_MONTH));
			} catch (ParseException e) {
				System.out.println("Could not parse date: " + date);
			}
		}
	}
	
	
	public static void init() {
		createDateFormat();
		tagDateFormat = new SimpleDateFormat("MMMMM");
	}
}
