package com.sunsigne.reversedrebecca.menu.submenu;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;

public class LanguageScreen extends SubMenuScreen {

	public LanguageScreen() {
		super();
		loadLangList();

		createLangButtons();
	}

	////////// NAME ////////////

	public String getName() {
		while (lang_list == null)
			loadLangList();

		return "lang_" + new FormatedString().getNumber(lang_list.size());
	}

	////////// SUB MENU ////////////

	protected MenuScreen getPreviousMenu() {
		return new TitleScreen();
	}

	////////// LANGUAGE ////////////

	private ArrayList<String> lang_list;

	private void loadLangList() {
		File file = new File(FilePath.RESSOURCES_PATH + "texts");
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));
		lang_list = new ArrayList<String>();

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".") == false) // check if folder or file
				lang_list.add(tempfile);
		});

		while (lang_list.size() > 12) // max size is 12
			lang_list.remove(11);

		if (lang_list.size() < 2) // less than 2 langs (which is not possible)
			stopApp();
	}

	private void stopApp() {
		new SoundTask().play(SOUNDTYPE.ERROR, "error");
		JOptionPane.showMessageDialog(null, "An error has occurred, some languages files are missing");
		new Conductor().stopApp();
	}

	////////// BUTTONS ////////////

	private void createLangButtons() {
		switch (lang_list.size()) {

		case 2:
			createLangButton(0, 416, 104);
			createLangButton(1, 416, 208);
			break;

		case 3:
			createLangButton(0, 416, 51);
			createLangButton(1, 416, 155);
			createLangButton(2, 416, 259);
			break;

		case 4:
			createButtonRow(0, 1, 104);
			createButtonRow(2, 3, 208);
			break;

		case 5:
			createButtonRow(0, 1, 51);
			createButtonRow(2, 3, 155);
			createLangButton(4, 416, 259);
			break;

		case 6:
			createButtonRow(0, 1, 51);
			createButtonRow(2, 3, 155);
			createButtonRow(4, 5, 259);
			break;

		case 7:
			createButtonRow(0, 1, 0);
			createButtonRow(2, 3, 104);
			createButtonRow(4, 5, 208);
			createLangButton(6, 416, 312);
			break;

		case 8:
			createButtonRow(0, 1, 0);
			createButtonRow(2, 3, 104);
			createButtonRow(4, 5, 208);
			createButtonRow(6, 7, 312);
			break;

		case 9:
			createButtonRow(0, 1, 2, 51);
			createButtonRow(3, 4, 5, 155);
			createButtonRow(6, 7, 8, 259);
			break;

		case 10:
			createButtonRow(0, 1, 2, 0);
			createButtonRow(3, 4, 5, 104);
			createButtonRow(6, 7, 8, 208);
			createLangButton(9, 416, 312);
			break;

		case 11:
			createButtonRow(0, 1, 2, 0);
			createButtonRow(3, 4, 5, 104);
			createButtonRow(6, 7, 8, 208);
			createButtonRow(9, 10, 312);
			break;

		case 12:
			createButtonRow(0, 1, 2, 0);
			createButtonRow(3, 4, 5, 104);
			createButtonRow(6, 7, 8, 208);
			createButtonRow(9, 10, 11, 312);
			break;
		}
	}

	private void createButtonRow(int index1, int index2, int y) {
		createLangButton(index1, 206, y);
		createLangButton(index2, 623, y);
	}

	private void createButtonRow(int index1, int index2, int index3, int y) {
		createLangButton(index1, 0, y);
		createLangButton(index2, 416, y);
		createLangButton(index3, 833, y);
	}

	private void createLangButton(int index, int x, int y) {
		String name = lang_list.get(index);
		GenericListener onPress = () -> chooseLanguage(name);
		String text = new FileTask().read(false, "texts/" + name + "/lang/name" + ".txt");

		ButtonObject button = new TitleScreenButton(text, x + 325, y + 503, 415, 80, onPress, null);
		LAYER.MENU.getHandler().addObject(button);
	}

	////////// BUTTON ACTION ////////////

	private void chooseLanguage(String language) {
		Language.getInstance().setLang(language);
		getPreviousMenu();
	}

}
