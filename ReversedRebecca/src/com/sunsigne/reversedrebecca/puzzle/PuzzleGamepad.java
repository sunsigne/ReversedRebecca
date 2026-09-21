package com.sunsigne.reversedrebecca.puzzle;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePreseting;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public abstract class PuzzleGamepad extends Puzzle implements GamepadEvent, MousePreseting {

	public PuzzleGamepad(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);

		loadGamepadSetup();
	}

	////////// USEFULL ////////////

	public static int getCol(float col) {
		return (int) (2 * Size.XS + col * Size.L);
	}

	public static int getRow(float row) {
		return (int) (Size.XS + row * Size.L);
	}

	////////// PRESET MOUSE POS ////////////

	private PresetMousePos preset;

	@Override
	public PresetMousePos getPreset() {
		return preset;
	}

	@Override
	public void setPreset(PresetMousePos preset) {
		this.setPreset(preset, true);
	}

	public void setPreset(PresetMousePos preset, boolean sound) {
		this.preset = preset;
		preset.moveMouse();

		if (isPresetNull() == false && sound)
			new SoundTask().playSound(SOUNDTYPE.SOUND, "gamepad");
	}

	protected void loadGamepadSetup() {
		if (ControllerManager.getInstance().isUsingGamepad())
			setPreset(getDefaultPreset(), false);
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	private boolean pressingButton;

	protected boolean pressingButton() {
		if (pressingButton)
			return true;

		pressingButton = true;
		new GameTimer(3, true, () -> pressingButton = false);
		return false;
	}

}
