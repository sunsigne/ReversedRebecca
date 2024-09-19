package com.sunsigne.reversedrebecca.system;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebTask {

	////////// USEFUL ////////////

	public String getValidUrl(String text) {
		return text.replace(" ", "%20").replace("=", "%3D").replace("\"", "%22")
				.replace(System.getProperty("line.separator"), "%0D%0A");
	}

	////////// HTML ////////////

	public static final String CRASH_LINK_FR = "https://form.dragnsurvey.com/survey/r/04b4b3ff";
	public static final String CRASH_LINK_ENG = "https://form.dragnsurvey.com/survey/r/535fb02b";

	public static final String STEAM_LINK_HTTP = "https://store.steampowered.com/app/2297990/Reversed_Rebecca/";
	public static final String STEAM_LINK = "steam://store/2297990/";
	
	public static final String ITCHIO_LINK = "https://sunsigne.itch.io/reversedrebecca";

	public void openSteam(String steamLink, String httpLink) {
		String app = "C://Program Files (x86)//Steam";
		File file = new File(app);

		if (file.exists())
			openHtml(steamLink);
		else
			openHtml(httpLink);
	}

	public void openHtml(String link) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(link));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				Runtime.getRuntime().exec("xdg-open " + link);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
