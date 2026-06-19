package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class OptionsScreen extends SubMenuScreen {

	public OptionsScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);

		createGeneralButton();
		createControlsButton();
		createAudioButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "options";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.OPTION);
	}

	////////// BUTTONS ////////////

	private void createOptionScreenButton(String text, PresetMousePos preset, int x, int y, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 325 + x, 503 + y, 415, 80, onPress, null);
		LAYER.MENU.addObject(button);
		buttons.put(preset, button);
	}

	private void createGeneralButton() {
		GenericListener onPress = () -> new GeneralScreen();
		createOptionScreenButton(translate("GeneralButton"), GENERAL, 416, 51, onPress);
	}

	private void createControlsButton() {
		GenericListener onPress = () -> new ControlsScreen();
		createOptionScreenButton(translate("ControlsButton"), CONTROLS, 416, 155, onPress);
	}

	private void createAudioButton() {
		GenericListener onPress = () -> new AudioScreen();
		createOptionScreenButton(translate("AudioButton"), AUDIO, 416, 259, onPress);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos GENERAL = new PresetMousePos(945, 590);
	public static final PresetMousePos CONTROLS = new PresetMousePos(945, 700);
	public static final PresetMousePos AUDIO = new PresetMousePos(945, 805);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(GENERAL);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == GENERAL)
			generalPressed(e);
		else if (getPreset() == CONTROLS)
			controlsPressed(e);
		else if (getPreset() == AUDIO)
			audioPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void generalPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(CONTROLS);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(GENERAL).mousePressed(null);
	}

	private void controlsPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(GENERAL);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(AUDIO);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(CONTROLS).mousePressed(null);
	}

	private void audioPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(CONTROLS);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(AUDIO).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(AUDIO);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
