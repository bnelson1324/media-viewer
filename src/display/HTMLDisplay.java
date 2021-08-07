package display;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HTMLDisplay {

	private static int port = 8080;
	
	public static void init() {
		
		try {
			Desktop.getDesktop().browse(new URI("http://localHost:" + port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
