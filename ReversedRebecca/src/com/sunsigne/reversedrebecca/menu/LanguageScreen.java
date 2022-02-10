package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LanguageScreen implements Updatable {

	private String file = "menu.csv";
	
	public LanguageScreen() {
		LAYER.MENU.addObject(this);

		createLangButtons();
		createBackButton();
	}

	////////// BUTTONS ////////////

	///// language /////

	private void createLangButtons() {

		File file = new File("ressources/texts");
		var lang_list = new ArrayList<String>(Arrays.asList(file.list()));

		int x = 0;

		for (String tempLang : lang_list) {
			GenericListener onPress = () -> chooseLanguage(tempLang);
			GenericListener onRelease = null;

			String text = new FileTask().read("/texts/" + tempLang + "/lang/name" + ".txt");

			ButtonObject languageButton = new TitleScreenButton(text, 410 + x, 300, 500, 90, onPress, onRelease);
			LAYER.MENU.addObject(languageButton);

			x += 600;
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

		ButtonObject backButton = new TitleScreenButton(text, Window.WIDHT - widht - 20, Window.HEIGHT - height - 10, widht,
				height, onPress, onRelease);
		backButton.setFacing(DIRECTION.RIGHT);
		LAYER.MENU.addObject(backButton);
	}

	private void openTitleScreen() {
		getHandler().clear();
		new TitleScreen();
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);

		g.setColor(Color.BLUE);
		g.fillRect(380, 280, 1200, 150);
		g.setColor(Color.YELLOW);
		g.drawRect(380, 280, 1200, 150);
	}

}
