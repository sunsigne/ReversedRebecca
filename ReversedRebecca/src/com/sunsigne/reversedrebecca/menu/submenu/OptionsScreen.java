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
		createVideoButton();
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
		createOptionScreenButton(translate("GeneralButton"), GENERAL, 206, 104, onPress);
	}

	private void createControlsButton() {
		GenericListener onPress = () -> new ControlsScreen();
		createOptionScreenButton(translate("ControlsButton"), CONTROLS, 623, 104, onPress);
	}

	private void createAudioButton() {
		GenericListener onPress = () -> new AudioScreen();
		createOptionScreenButton(translate("AudioButton"), AUDIO, 206, 208, onPress);
	}

	private void createVideoButton() {
		GenericListener onPress = () -> new VideoScreen();
		createOptionScreenButton(translate("VideoButton"), VIDEO, 623, 208, onPress);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos GENERAL = new PresetMousePos(730, 650);
	public static final PresetMousePos CONTROLS = new PresetMousePos(1150, 650);
	public static final PresetMousePos AUDIO = new PresetMousePos(730, 750);
	public static final PresetMousePos VIDEO = new PresetMousePos(1150, 750);

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
			gamePressed(e);
		else if (getPreset() == CONTROLS)
			controlsPressed(e);
		else if (getPreset() == AUDIO)
			audioPressed(e);
		else if (getPreset() == VIDEO)
			videoPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void gamePressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.RIGHT)
			setPreset(CONTROLS);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(AUDIO);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(GENERAL).mousePressed(null);
	}

	private void controlsPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(GENERAL);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(VIDEO);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(CONTROLS).mousePressed(null);
	}

	private void audioPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.RIGHT)
			setPreset(VIDEO);
		else if (e.getKey() == ButtonEvent.UP)
			setPreset(GENERAL);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(AUDIO).mousePressed(null);
	}

	private void videoPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(AUDIO);
		else if (e.getKey() == ButtonEvent.UP)
			setPreset(CONTROLS);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(VIDEO).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(AUDIO);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
