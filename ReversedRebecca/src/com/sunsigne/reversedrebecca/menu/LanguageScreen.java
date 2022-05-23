package com.sunsigne.reversedrebecca.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.Window;

public class LanguageScreen extends MenuScreen {

	private static final MENU menu = MENU.NAV;

	public LanguageScreen() {
		super(menu);
	}

	@Override
	protected void createNewMenu() {
		loadLangList();

		createLangButtons();
		createBackButton();
	}

	////////// LANGUAGE ////////////

	private ArrayList<String> lang_list;

	private void loadLangList() {
		File file = new File("ressources/texts");
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));
		lang_list = new ArrayList<String>();

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".") == false) // check if folder or file
				lang_list.add(tempfile);
		});

		while (lang_list.size() > 15)
			lang_list.remove(14);

		if (lang_list.size() < 2)
			stopApp();
	}

	private void stopApp() {
		new SoundTask().play(SOUNDTYPE.ERROR, "sound/error");
		JOptionPane.showMessageDialog(null, "An error has occurred, some languages files are missing");
		new Conductor().stopApp();
	}

	////////// BUTTONS ////////////

	private void createLangButtons() {
		switch (lang_list.size()) {

		case 2:
			createButtonRow(0, 1, 235);
			break;

		case 3:
			createButtonRow(0, 1, 2, 235);
			break;

		case 4:
			createButtonRow(0, 1, 160);
			createButtonRow(2, 3, 261);
			break;

		case 5:
			createButtonRow(0, 1, 100);
			createButtonRow(2, 3, 201);
			createLangButton(4, 415, 302);
			break;

		case 6:
			createButtonRow(0, 1, 100);
			createButtonRow(2, 3, 201);
			createButtonRow(4, 5, 302);
			break;

		case 7:
			createButtonRow(0, 1, 60);
			createButtonRow(2, 3, 161);
			createButtonRow(4, 5, 262);
			createLangButton(6, 415, 363);
			break;

		case 8:
			createButtonRow(0, 1, 60);
			createButtonRow(2, 3, 161);
			createButtonRow(4, 5, 262);
			createButtonRow(6, 7, 363);
			break;

		case 9:
			createButtonRow(0, 1, 2, 120);
			createButtonRow(3, 4, 5, 221);
			createButtonRow(6, 7, 8, 322);
			break;

		case 10:
			createButtonRow(0, 1, 0);
			createButtonRow(2, 3, 101);
			createButtonRow(4, 5, 202);
			createButtonRow(6, 7, 303);
			createButtonRow(8, 9, 404);
			break;

		case 11:
			createButtonRow(0, 1, 2, 60);
			createButtonRow(3, 4, 5, 161);
			createButtonRow(6, 7, 8, 262);
			createButtonRow(9, 10, 363);
			break;

		case 12:
			createButtonRow(0, 1, 2, 60);
			createButtonRow(3, 4, 5, 161);
			createButtonRow(6, 7, 8, 262);
			createButtonRow(9, 10, 11, 363);
			break;

		case 13:
			createButtonRow(0, 1, 2, 0);
			createButtonRow(3, 4, 5, 101);
			createButtonRow(6, 7, 8, 202);
			createButtonRow(9, 10, 303);
			createButtonRow(11, 12, 404);
			break;

		case 14:
			createButtonRow(0, 1, 2, 0);
			createButtonRow(3, 4, 5, 101);
			createButtonRow(6, 7, 8, 202);
			createButtonRow(9, 10, 11, 303);
			createButtonRow(12, 13, 404);
			break;

		case 15:
			createButtonRow(0, 1, 2, 0);
			createButtonRow(3, 4, 5, 101);
			createButtonRow(6, 7, 8, 202);
			createButtonRow(9, 10, 11, 303);
			createButtonRow(12, 13, 14, 404);
			break;
		}
	}

	private void createButtonRow(int index1, int index2, int y) {
		createLangButton(index1, 205, y);
		createLangButton(index2, 625, y);
	}

	private void createButtonRow(int index1, int index2, int index3, int y) {
		createLangButton(index1, 0, y);
		createLangButton(index2, 415, y);
		createLangButton(index3, 830, y);
	}

	private void createLangButton(int index, int x, int y) {
		String name = lang_list.get(index);
		GenericListener onPress = () -> chooseLanguage(name);
		String text = new FileTask().read("/texts/" + name + "/lang/name" + ".txt");

		ButtonObject button = new TitleScreenButton(text, x + 320, y + 271, 415, 80, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		menu.getHandler().addObject(button);
	}

	private void createBackButton() {
		GenericListener onPress = () -> openTitleScreen();
		String text = new Translatable().getTranslatedText("BackButton", file);

		int widht = 420;
		int height = 90;

		ButtonObject button = new TitleScreenButton(text, Window.WIDHT - widht - 30, 0, widht, height, onPress, null);
		button.setFacing(DIRECTION.RIGHT);
		menu.getHandler().addObject(button);
	}

	////////// USEFUL ////////////

	private void chooseLanguage(String language) {
		Language.getInstance().setLang(language);
		LAYER.MENU_MAIN.getHandler().clear();
		new TitleScreen();
	}

	private void openTitleScreen() {
		TitleScreen titleScreen = new TitleScreen();
		titleScreen.createFlagLanguageButton();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/menu/lang/" + new FormatedString().getNumber(lang_list.size()));
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);
		int width = 153;
		int height = 59;
		g.drawImage(getImage(), 420 - width, 290 - height, 1050 + 2 * width, 450 + 2 * height, null);
	}

}
