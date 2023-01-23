package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.world.World;

public class QuitScreen extends MenuIngameScreen {

	public QuitScreen() {
		super();
		loadText();

		createQuitButton();
		createBackButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "quit";
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		TitleScreenText quitText;
		int x = 325 + 416;
		int y = 343;

		text = translate("Quit" + "Detail" + "1");
		quitText = new TitleScreenText(text, x, y + 245);
		quitText.setFontSize(18f);
		LAYER.MENU.addObject(quitText);

		text = translate("Quit" + "Detail" + "2");
		quitText = new TitleScreenText(text, x, y + 280);
		quitText.setFontSize(18f);
		LAYER.MENU.addObject(quitText);
	}

	////////// BUTTONS ////////////

	private void createOptionScreenButton(String text, int x, int y, GenericListener onPress) {
		this.createOptionScreenButton(text, x, y, onPress, "button");
	}

	private void createOptionScreenButton(String text, int x, int y, GenericListener onPress, String sound) {
		ButtonObject button = new TitleScreenButton(text, 325 + x, 343 + y, 415, 80, onPress, null) {
			public String getSound() {
				return sound;
			}
		};

		LAYER.MENU.addObject(button);
	}

	private void createQuitButton() {
		GenericListener onPress = () -> loadTitleScreen();
		createOptionScreenButton(translate("Confirm"), 416, 51, onPress);
	}

	private void createBackButton() {
		GenericListener onPress = () -> new ResumeScreen();
		createOptionScreenButton(translate("Cancel"), 416, 155, onPress, "button_back");
	}

	////////// BUTTON ACTION ////////////

	private void loadTitleScreen() {
		LAYER.LOADING.addObject(new LoadingScreen());

		new MenuIngameController().unloadResumeScreen();
		World.get().destroy();
		new TitleScreen();

		LAYER.LOADING.getHandler().clear();
	}

}
