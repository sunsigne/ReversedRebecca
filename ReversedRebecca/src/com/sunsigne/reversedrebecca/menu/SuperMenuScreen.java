package com.sunsigne.reversedrebecca.menu;

import java.util.HashMap;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePreseting;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class SuperMenuScreen implements Updatable, TickFree, GamepadEvent, MousePreseting {

	public SuperMenuScreen(PresetMousePos defaultPreset) {
		LAYER.MENU.getHandler().clear();
		LAYER.MENU.addObject(this);
		loadGamepadSetup(defaultPreset);
	}

	////////// USEFUL ////////////

	protected String translate(String text) {
		return new Translatable().getTranslatedText(text, FilePath.MENU);
	}

	////////// PRESET MOUSE POS ////////////

	protected HashMap<PresetMousePos, ButtonObject> buttons = new HashMap<>();

	private PresetMousePos preset;
	private PresetMousePos defaultPreset;

	@Override
	public PresetMousePos getDefaultPreset() {
		return defaultPreset;
	}

	@Override
	public PresetMousePos getPreset() {
		return preset;
	}

	@Override
	public void setPreset(PresetMousePos preset) {
		this.setPreset(preset, true);
	}

	private void setPreset(PresetMousePos preset, boolean sound) {
		this.preset = preset;
		preset.moveMouse();

		if (isPresetNull() == false && sound)
			new SoundTask().playSound(SOUNDTYPE.SOUND, "gamepad");
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	protected void loadGamepadSetup(PresetMousePos defaultPreset) {
		this.defaultPreset = defaultPreset;

		if (ControllerManager.getInstance().isUsingGamepad())
			setPreset(defaultPreset, false);
	}

	private boolean pressingButton;

	protected boolean pressingButton() {
		if (pressingButton)
			return true;

		pressingButton = true;
		new GameTimer(3, () -> pressingButton = false);
		return false;
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
