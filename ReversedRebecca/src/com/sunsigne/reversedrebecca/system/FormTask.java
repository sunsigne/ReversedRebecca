package com.sunsigne.reversedrebecca.system;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FormTask {

	private final String LINK = "https://form.dragnsurvey.com/survey/r/04b4b3ff";

	////////// USEFUL ////////////

	public String getValidUrl(String text) {
		return text.replace(" ", "%20").replace("=", "%3D").replace("\"", "%22")
				.replace(System.getProperty("line.separator"), "%0D%0A");
	}

	////////// EMAIL ////////////

	public void sendRequest() {
		try {
			Desktop.getDesktop().browse(new URI(LINK));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
