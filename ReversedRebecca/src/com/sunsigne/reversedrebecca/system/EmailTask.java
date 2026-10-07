package com.sunsigne.reversedrebecca.system;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class EmailTask {

	////////// USEFUL ////////////

	public String getValidUrl(String text) {
		return text.replace(" ", "%20").replace("=", "%3D").replace("\"", "%22")
				.replace(System.getProperty("line.separator"), "%0D%0A");
	}

	////////// EMAIL ////////////

	private final String email = "exemple@exemple.com";

	public void sendRequest() {
		Desktop desktop = Desktop.getDesktop();

		String target = "mailto:" + email;
		String subject = "subject=" + getValidUrl("Reversed Rebeca - Mail Automatique");

		String mail = new FileTask().read(true, "mail.txt");
		String dev_data = new FileTask().read(true, "save.csv");

		String body = "body=" + getValidUrl(mail + dev_data);
		String message = target + "?" + subject + "&" + body;

		try {
			desktop.mail(new URI(message));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
