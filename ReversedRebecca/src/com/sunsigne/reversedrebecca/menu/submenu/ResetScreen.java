package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.world.World;

public class ResetScreen extends SubMenuScreen {

	public ResetScreen() {
		super();
		loadText();

		createResetButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "reset";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new GameScreen();
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

	////////// BUTTONS ////////////

	private void createResetButton() {
		GenericListener onPress = () -> resetProgression();
		ButtonObject button = new TitleScreenButton(translate("Confirm"), 741, 607, 415, 80, onPress, null) {
			
			@Override
			public String getSound() {
				return "button_validate";
			}
		};
		
		LAYER.MENU.addObject(button);
	}

	////////// BUTTON ACTION ////////////

	private void resetProgression() {
		LAYER.LOADING.addObject(new LoadingScreen());
		new Save().resetProgression();
		World.get().destroy();
		new TitleScreen();
	}

}
