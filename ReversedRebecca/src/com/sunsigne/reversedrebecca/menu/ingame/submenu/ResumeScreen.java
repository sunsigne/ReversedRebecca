package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class ResumeScreen extends MenuIngameScreen {

	public ResumeScreen() {
		super(RESUME);
		loadText();

		createResumeButton();
		createOptionsButton();
		createQuitButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "resume";
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		TitleScreenText quitText;
		int x = 325 + 416;
		int y = 473;

		text = translate("Resume" + "Detail" + "1");
		quitText = new TitleScreenText(text, x, y + 245);
		quitText.setFontSize(18f);
		LAYER.MENU.addObject(quitText);

		text = translate("Resume" + "Detail" + "2");
		quitText = new TitleScreenText(text, x, y + 280);
		quitText.setFontSize(18f);
		LAYER.MENU.addObject(quitText);
	}

	////////// BUTTONS ////////////

	private void createOptionScreenButton(String text, PresetMousePos preset, int x, int y, GenericListener onPress) {
		this.createOptionScreenButton(text, preset, x, y, onPress, "button");
	}

	private void createOptionScreenButton(String text, PresetMousePos preset, int x, int y, GenericListener onPress,
			String sound) {
		ButtonObject button = new TitleScreenButton(text, 325 + x, 343 + y, 415, 80, onPress, null) {
			public String getSound() {
				return sound;
			}
		};

		buttons.put(preset, button);
		LAYER.MENU.addObject(button);
	}

	private void createResumeButton() {
		GenericListener onPress = () -> new MenuIngameController().unloadResumeScreen();
		createOptionScreenButton(translate("ResumeButton"), RESUME, 416, 51, onPress, "button_back");
	}

	private void createOptionsButton() {
		GenericListener onPress = () -> new OptionsIngameScreen(OptionsIngameScreen.GENERAL);
		createOptionScreenButton(translate("OptionsButton"), OPTIONS, 416, 155, onPress);
	}

	private void createQuitButton() {
		GenericListener onPress = () -> new QuitScreen();
		createOptionScreenButton(translate("QuitButton"), QUIT, 416, 259, onPress);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos RESUME = new PresetMousePos(945, 430);
	public static final PresetMousePos OPTIONS = new PresetMousePos(945, 535);
	public static final PresetMousePos QUIT = new PresetMousePos(945, 640);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(RESUME);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(RESUME, false);
			buttons.get(RESUME).mousePressed(null);
		}

		else if (getPreset() == RESUME)
			resumePressed(e);
		else if (getPreset() == OPTIONS)
			optionsPressed(e);
		else if (getPreset() == QUIT)
			quitPressed(e);
	}

	private void resumePressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(OPTIONS);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(RESUME).mousePressed(null);
	}

	private void optionsPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(RESUME);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(QUIT);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(OPTIONS).mousePressed(null);
	}

	private void quitPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(OPTIONS);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(QUIT).mousePressed(null);
	}

}
