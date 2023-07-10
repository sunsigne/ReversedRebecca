package com.sunsigne.reversedrebecca.system;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sunsigne.reversedrebecca.ressources.lang.Language;

public class FormTask {

	private final String LINK_FR = "https://form.dragnsurvey.com/survey/r/04b4b3ff";
	private final String LINK_ENG = "https://form.dragnsurvey.com/survey/r/535fb02b";

	////////// USEFUL ////////////

	public String getValidUrl(String text) {
		return text.replace(" ", "%20").replace("=", "%3D").replace("\"", "%22")
				.replace(System.getProperty("line.separator"), "%0D%0A");
	}

	private String getLink() {
		String lang = Language.getInstance().getLang();

		if (lang.equalsIgnoreCase("french"))
			return LINK_FR;
		else if (lang.equalsIgnoreCase("english"))
			return LINK_ENG;
		else
			return LINK_ENG;

	}

	////////// REQUEST ////////////

	public void sendRequest() {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(getLink()));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				Runtime.getRuntime().exec("xdg-open " + getLink());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
