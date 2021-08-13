package display;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import io.javalin.Javalin;

public class HTMLDisplay {

	private static int port = 8080;
	private static Javalin app;
	
	public static void init() {
		// opens browser to web page
		try {
			Desktop.getDesktop().browse(new URI("http://localHost:" + port));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		// initializes Javalin server
		initServer();
	}
	
	private static void initServer() {
		app = Javalin.create( config -> {
			config.addStaticFiles("/pages");
			config.addStaticFiles("/storage_root");
		}).start(port);
		
		// which pages to render
		app.get("/", ctx -> ctx.render("/pages/index.html"));
		app.get("/view", ctx -> ctx.render("/pages/view.html"));
		app.get("/modifytags", ctx -> ctx.render("/pages/modifytags.html"));
		app.get("/settings", ctx -> ctx.render("/pages/settings.html"));
		app.get("/help", ctx -> ctx.render("/pages/help.html"));
		
		// search page
		app.get("/search", ctx -> {
			ctx.render("/pages/search.html");
		});
		app.get("/searchresults", ctx -> {
			// responds to AJAX requests from javascript
			String query = ctx.queryParam("q");
			HTTPHandler.handleSearch(ctx, query);
		});
	}
	
}
