package com.sunsigne.reversedrebecca.system;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class FormTask {

	private final String LINK = "https://form.dragnsurvey.com/survey/r/9389e570";

	////////// EMAIL ////////////

	public void sendRequest() {

		// copy link to clipboard
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection = new StringSelection(LINK);
		clipboard.setContents(selection, null);

		// show message to inform
		String message = "Le lien suivant a été copié :" + System.getProperty("line.separator") + LINK;
		JLabel label = new JLabel("<html><body><div width='100px' align='center'>" + message + "</div></body></html>");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JOptionPane.showMessageDialog(null, label);
	}

}
