package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class ResumeScreen extends MenuIngameScreen {

	public ResumeScreen() {
		super();
		createResumeButton();
		createOptionsButton();
		createQuitButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "resume";
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

	private void createResumeButton() {
		GenericListener onPress = () -> new MenuIngameController().unloadResumeScreen();
		createOptionScreenButton(translate("ResumeButton"), 416, 51, onPress, "button_back");
	}

	private void createOptionsButton() {
		// GenericListener onPress = () -> new ControlsScreen();
		GenericListener onPress = () -> {
		};
		createOptionScreenButton(translate("OptionsButton"), 416, 155, onPress);
	}

	private void createQuitButton() {
		GenericListener onPress = () -> new QuitScreen();
		createOptionScreenButton(translate("QuitButton"), 416, 259, onPress);
	}

}