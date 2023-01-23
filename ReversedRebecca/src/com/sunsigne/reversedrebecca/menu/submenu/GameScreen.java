package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class GameScreen extends SubMenuScreen {

	public GameScreen() {
		super();
		createResetButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "game";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new OptionsScreen();
	}

	////////// BUTTONS ////////////

	private void createResetButton() {
		GenericListener onPress = () -> new ResetScreen();
		ButtonObject button = new TitleScreenButton(translate("Reset"), 741, 658, 415, 80, onPress, null) {
			
			@Override
			public String getSound() {
				return "button_validate";
			}
		};
		
		LAYER.MENU.addObject(button);
	}

}
