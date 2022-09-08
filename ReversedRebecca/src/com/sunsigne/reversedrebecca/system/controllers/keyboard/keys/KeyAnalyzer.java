package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class KeyAnalyzer {

	private int key; 

	public KeyAnalyzer(int key) {
		this.key = key;
	}

	public boolean isAllowedKey() {
		
		if (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_TAB || key == KeyEvent.VK_ENTER)
			return true;

		if (key == KeyEvent.VK_SHIFT || key == KeyEvent.VK_CONTROL || key == KeyEvent.VK_ALT)
			return true;
		
		if (key == KeyEvent.VK_SPACE)
			return true;

		// 0 to 9
		if (key >= 48 && key <= 57)
			return true;

		// A to Z
		if (key >= 65 && key <= 90)
			return true;

		// NUMPAD
		if (key >= 96 && key <= 111 && key != 108)
			return true;

		// F1 to F12
		if (key >= 112 && key <= 123)
			return true;

		return false;
	}

	public String getKeyText() {

		String java_text = KeyEvent.getKeyText(key);

		if (java_text.length() == 1) // a single char
			return java_text;

		if (key >= 112 && key <= 123) // F1 to F12
			return java_text;

		if (key >= 96 && key <= 105) { // NUMPAD number
			String numpad = new Translatable().getTranslatedText("NUMPAD", FilePath.KEY);
			String num = java_text.substring(java_text.length() - 1);
			return numpad + "#" + num;
		}

		if (key >= 106 && key <= 111 && key != 108) // NUMPAD -> * + - . /
			return java_text.substring(java_text.length() - 1);

		if (key == 65535)
			return "...";
		
		return new Translatable().getTranslatedText(java_text, FilePath.KEY);
	}

}
