package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class GameScreen extends SubMenuScreen {

	public GameScreen() {
		super();
		loadText();
		
		createResetButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "game";
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		TitleScreenText resetDetail;
		int x = 325 + 416;
		int y = 503 + 104;

		// your progress will be ...
		text = translate("ResetDetail" + "1");
		resetDetail = new TitleScreenText(text, x, y + 146 - 56);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);

		// ... permanently lost
		text = translate("ResetDetail" + "2");
		resetDetail = new TitleScreenText(text, x, y + 181 - 56);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new OptionsScreen();
	}

	////////// BUTTONS ////////////

	private void createResetButton() {
		GenericListener onPress = () -> new ResetScreen();
		ButtonObject button = new TitleScreenButton(translate("Reset"), 741, 607, 415, 80, onPress, null) {
			
			@Override
			public String getSound() {
				return "button_validate";
			}
		};
		
		LAYER.MENU.addObject(button);
	}

}
