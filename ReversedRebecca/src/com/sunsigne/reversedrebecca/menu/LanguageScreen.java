package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LanguageScreen implements Updatable, TickFree {

	private String file = "menu.csv";

	public LanguageScreen() {
		LAYER.MENU.addObject(this);

		createLangButtons();
		createBackButton();
	}

	////////// BUTTONS ////////////

	///// language /////

	private ArrayList<String> getLangList() {
		File file = new File("ressources/texts");
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));

		ArrayList<String> lang_list = new ArrayList<String>();
		file_list.forEach(tempfile -> {
			if (tempfile.contains(".ini") == false)
				lang_list.add(tempfile);
		});
		return lang_list;
	}
	
	int height = 0;
	
	private void createLangButtons() {
		ArrayList<String> lang_list = getLangList();
		int size = lang_list.size();
		int x = 0;

		for (int index = 0; index < size; index++) {

			if (index != 0) {
				if (index % 2 == 0) {
					x = 0;
					height += 140;
				} else
					x = 600;
			}

			String tempLang = lang_list.get(index);

			GenericListener onPress = () -> chooseLanguage(tempLang);
			GenericListener onRelease = null;

			String text = new FileTask().read("/texts/" + tempLang + "/lang/name" + ".txt");

			ButtonObject languageButton = new TitleScreenButton(text, 410 + x, 480 + height, 500, 90, onPress, onRelease);
			LAYER.MENU.addObject(languageButton);
		}
	}

	private void chooseLanguage(String language) {
		Language.getInstance().setLang(language);
		openTitleScreen();
	}

	///// back /////

	private void createBackButton() {
		GenericListener onPress = () -> openTitleScreen();
		GenericListener onRelease = null;

		String text = "< " + new Translatable().getTranslatedText("BackButton", file);

		int widht = 420;
		int height = 90;

		ButtonObject backButton = new TitleScreenButton(text, Window.WIDHT - widht - 20, Window.HEIGHT - height - 10,
				widht, height, onPress, onRelease);
		backButton.setFacing(DIRECTION.RIGHT);
		LAYER.MENU.addObject(backButton);
	}

	private void openTitleScreen() {
		getHandler().clear();
		new TitleScreen();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);

		Color light_yellow = new Color(255, 204, 0);
		Color dark_yellow = new Color(255, 163, 0, 80);
		int[] rect = new int[] { 380, 460 - height / 2, 1200, 150 + height / 2 };

		g.setColor(dark_yellow);
		g.fillRect(rect[0] - 7, rect[1] - 7, rect[2] + 2 * 7, rect[3] + 2 * 7);

		g.setColor(light_yellow);
		g.fillRect(rect[0] - 5, rect[1] - 5, rect[2] + 2 * 5, rect[3] + 2 * 5);

		Graphics2D g2d = (Graphics2D) g;
		GradientPaint paint = new GradientPaint(0, rect[1], new Color(220, 220, 255), 0, rect[1] + rect[3],
				new Color(0, 0, 200));
		g2d.setPaint(paint);
		g2d.fillRect(rect[0], rect[1], rect[2], rect[3]);
	}

}
