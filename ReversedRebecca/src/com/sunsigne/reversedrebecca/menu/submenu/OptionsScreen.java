package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class OptionsScreen extends SubMenuScreen {

	public OptionsScreen() {
		super();

		createAudioButton();
		createVideoButton();
		createControlsButton();
		createGameButton();
	}

	////////// NAME ////////////

	public String getName() {
		return "options";
	}

	////////// SUB MENU ////////////

	protected MenuScreen getPreviousMenu() {
		return new TitleScreen();
	}

	////////// BUTTONS ////////////

	private void createOptionScreenButton(String text, int x, int y, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 325 + x, 503 + y, 415, 80, onPress, null);
		LAYER.MENU.addObject(button);
	}

	private void createAudioButton() {
		GenericListener onPress = null;
		String text = new Translatable().getTranslatedText("AudioButton", file);
		createOptionScreenButton(text, 206, 104, onPress);
	}
	
	private void createVideoButton() {
		GenericListener onPress = () -> new VideoScreen();
		String text = new Translatable().getTranslatedText("VideoButton", file);
		createOptionScreenButton(text, 623, 104, onPress);
	}
	
	private void createControlsButton() {
		GenericListener onPress = null;
		String text = new Translatable().getTranslatedText("ControlsButton", file);
		createOptionScreenButton(text, 206, 208, onPress);
	}
	
	private void createGameButton() {
		GenericListener onPress = null;
		String text = new Translatable().getTranslatedText("GameButton", file);
		createOptionScreenButton(text, 623, 208, onPress);
	}

}
