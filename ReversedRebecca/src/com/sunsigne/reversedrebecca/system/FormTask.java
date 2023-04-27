package com.sunsigne.reversedrebecca.system;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JOptionPane;

public class FormTask {

	private final String LINK = "https://form.dragnsurvey.com/survey/r/04b4b3ff";

	////////// EMAIL ////////////

	public void sendRequest() {

		// copy link to clipboard
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection = new StringSelection(LINK);
		clipboard.setContents(selection, null);

		// writing message
		String message = "Le lien suivant a été copié :" + System.getProperty("line.separator") + LINK;
		message = message.concat(System.getProperty("line.separator") + System.getProperty("line.separator"));
		message = message.concat("Ce lien vous permettra de soummettre un rapport d'erreur.");
		message = message.concat(System.getProperty("line.separator") + System.getProperty("line.separator"));
		message = message.concat("Merci également de renseigner dans votre rapport le contenu éventuel");
		message = message.concat(System.getProperty("line.separator"));
		message = message.concat("du ou de vos fichers \"crash_report.txt\", puis de les supprimer.");

		// show message to inform
		new BugFinder().openUserData();
		JOptionPane.showMessageDialog(null, message);
	}

}
