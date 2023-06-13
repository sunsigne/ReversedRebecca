package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.world.World;

public class ResetIngameScreen extends MenuIngameScreen {

	public ResetIngameScreen() {
		super(RESET);
		loadText();

		createConfirmButton();
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

		// your progress will be ...
		text = translate("ResetDetail" + "1");
		quitText = new TitleScreenText(text, x, y + 245);
		quitText.setFontSize(18f);
		LAYER.MENU.addObject(quitText);

		// ... permanently lost
		text = translate("ResetDetail" + "2");
		quitText = new TitleScreenText(text, x, y + 280);
		quitText.setFontSize(18f);
		LAYER.MENU.addObject(quitText);
	}

	////////// BUTTONS ////////////

	private void createGeneralScreenButton(String text, PresetMousePos preset, int x, int y, GenericListener onPress,
			String sound) {
		ButtonObject button = new TitleScreenButton(text, 325 + x, 343 + y, 415, 80, onPress, null) {
			public String getSound() {
				return sound;
			}
		};

		buttons.put(preset, button);
		LAYER.MENU.addObject(button);
	}

	private void createConfirmButton() {
		GenericListener onPress = () -> resetProgression();
		createGeneralScreenButton(translate("Confirm"), RESET, 416, 51, onPress, "button_validate");
	}

	private void createBackButton() {
		GenericListener onPress = () -> new OptionsIngameScreen(OptionsIngameScreen.BACK);
		createGeneralScreenButton(translate("Cancel"), BACK, 416, 155, onPress, "button_back");
	}

	////////// BUTTON ACTION ////////////

	private void resetProgression() {
		LAYER.LOADING.addObject(new LoadingScreen());

		new MenuIngameController().unloadResumeScreen();
		new Save().resetProgression();
		World.get().destroy();
		new TitleScreen(TitleScreen.PLAY);

		LAYER.LOADING.getHandler().clear();
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos RESET = new PresetMousePos(945, 430);
	public static final PresetMousePos BACK = new PresetMousePos(945, 535);

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
			quitPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void quitPressed(ButtonEvent e) {
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
