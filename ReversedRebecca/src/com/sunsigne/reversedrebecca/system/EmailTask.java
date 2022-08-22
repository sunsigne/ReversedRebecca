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

	public void sendRequest() {
		Desktop desktop = Desktop.getDesktop();

		String target = "mailto:" + "florian.murcia@hotmail.fr";
		String subject = "subject=" + getValidUrl("Reversed Rebeca - Questionnaire");

		String quiz = new FileTask().read(true, "quiz.txt");
		String dev_data = new FileTask().read(true, "dev_data.txt");

		String body = "body=" + getValidUrl(quiz + dev_data);
		String message = target + "?" + subject + "&" + body;

		try {
			desktop.mail(new URI(message));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
