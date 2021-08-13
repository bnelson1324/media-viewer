package display;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.javalin.http.Context;
import media_control.MediaHandler;

public class HTTPHandler {

	/* Search */
	protected static void handleSearch(Context ctx, String query) {
		if(query != null) {
			// create a json with search results, and send that to browser
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode rootNode = mapper.createObjectNode();
			ArrayNode mediaItemsArrayNode = rootNode.putArray("mediaItems");
			
			ArrayList<Path> results = MediaHandler.getMediaItemsByTag(query);
			for(Path mi : results) {
				ObjectNode MINode = mediaItemsArrayNode.addObject();
				
				MINode.put("fileName", mi.getFileName().toString());
				MINode.put("path", mi.toString());
				
				String fileType = "";
				try {
					fileType = Files.probeContentType(mi).split("/")[0];
				} catch (IOException e) {
					e.printStackTrace();
				}
				MINode.put("fileType", fileType);
			}
			try {
				String jsonString = mapper.writeValueAsString(rootNode);
				ctx.result(jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Modify tags */
	protected static void modifyTags(Context ctx) {
		// !! TODO 
	}
	
}
