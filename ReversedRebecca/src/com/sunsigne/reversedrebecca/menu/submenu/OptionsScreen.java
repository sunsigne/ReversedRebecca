package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.LockedTitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.achievement.Achievement;
import com.sunsigne.reversedrebecca.ressources.achievement.AchievementList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.world.World;

public class OptionsScreen extends SubMenuScreen {

	public OptionsScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);

		createGeneralButton();
		createControlsButton();
		createAudioButton();
		createBonusButton();
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
		createLockedOptionScreenButton(text, preset, x, y, onPress, false);
	}

	private void createLockedOptionScreenButton(String text, PresetMousePos preset, int x, int y,
			GenericListener onPress, boolean locked) {

		ButtonObject button;
		if (locked)
			button = new LockedTitleScreenButton(text, 325 + x, 503 + y, 415, 80, onPress, null);
		else
			button = new TitleScreenButton(text, 325 + x, 503 + y, 415, 80, onPress, null);

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

	private boolean bonusUnlocked;

	private void createBonusButton() {
		GenericListener onPress = () -> new World("dave_bonus");

		Achievement achievement = null;
		for (Achievement tempAchievement : AchievementList.getList().getList()) {
			if (tempAchievement.getName().equalsIgnoreCase("bonus") == false)
				continue;
			achievement = tempAchievement;
			break;
		}

		bonusUnlocked = false;
		if (achievement != null)
			bonusUnlocked = achievement.isUnlocked();

		createLockedOptionScreenButton(translate("BonusButton"), BONUS, 623, 208, onPress, bonusUnlocked == false);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos GENERAL = new PresetMousePos(730, 650);
	public static final PresetMousePos CONTROLS = new PresetMousePos(1150, 650);
	public static final PresetMousePos AUDIO = new PresetMousePos(730, 750);
	public static final PresetMousePos BONUS = new PresetMousePos(1150, 750);

	public void setPreset(boolean condition, PresetMousePos presetIfTrue, PresetMousePos presetIfFalse) {
		if (bonusUnlocked)
			this.setPreset(presetIfTrue, true);
		else
			this.setPreset(presetIfFalse, true);
	}

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
		else if (getPreset() == BONUS)
			bonusPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void generalPressed(ButtonEvent e) {
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
			setPreset(bonusUnlocked, BONUS, AUDIO);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(CONTROLS).mousePressed(null);
	}

	private void audioPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.RIGHT) {
			if (bonusUnlocked)
				setPreset(BONUS);
		} else if (e.getKey() == ButtonEvent.UP)
			setPreset(GENERAL);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(AUDIO).mousePressed(null);
	}

	private void bonusPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(AUDIO);
		else if (e.getKey() == ButtonEvent.UP)
			setPreset(CONTROLS);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BONUS).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(AUDIO);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
