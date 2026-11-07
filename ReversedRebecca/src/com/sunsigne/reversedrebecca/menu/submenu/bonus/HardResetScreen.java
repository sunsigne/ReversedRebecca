package com.sunsigne.reversedrebecca.menu.submenu.bonus;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.menu.submenu.SubMenuScreen;
import com.sunsigne.reversedrebecca.object.menu.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.World;

public class HardResetScreen extends SubMenuScreen {

	public HardResetScreen() {
		super(RESET);
		loadText();

		createResetButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "hard_reset";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new SettingsScreen();
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		TitleScreenText resetDetail;
		int x = 325 + 416;
		int y = 503 + 51;

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

		// Dave never foget
		text = translate("HardResetDetail" + "3");
		resetDetail = new TitleScreenText(text, x, y + 280 - 56);
		resetDetail.setFontSize(20f);
		LAYER.MENU.addObject(resetDetail);
	}

	////////// BUTTONS ////////////

	private void createResetButton() {
		GenericListener onPress = () -> resetProgression();
		ButtonObject button = new TitleScreenButton(translate("HardReset"), 325 + 416, 503 + 51, 415, 80, onPress,
				null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		LAYER.MENU.addObject(button);
		buttons.put(RESET, button);
	}

	////////// BUTTON ACTION ////////////

	private void resetProgression() {
		LAYER.LOADING.addObject(new LoadingScreen());
		Game.getInstance().forceLoop();
		new Save().hardResetProgression();
		World.get().destroy();
		new TitleScreen(TitleScreen.PLAY);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos RESET = new PresetMousePos(925, 600);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(RESET);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == RESET)
			resetPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void resetPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(RESET).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(RESET);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
