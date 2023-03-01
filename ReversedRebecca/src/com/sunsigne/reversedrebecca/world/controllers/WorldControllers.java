package com.sunsigne.reversedrebecca.world.controllers;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.world.World;

public abstract class WorldControllers implements KeyboardEvent, GamepadEvent {

	public abstract WorldControllers getWorldControllers();

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (World.get() == null)
			return;

		if (pressed)
			return;
		
		inputPressed(e.getKeyCode(), -1);
		pressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (World.get() == null)
			return;

		inputReleased(e.getKeyCode(), -1);
		pressed = false;
	}
	
	////////// GAMEPAD ////////////
	
	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (World.get() == null)
			return;
		
		inputPressed(65535, e.getKey());
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		if (World.get() == null)
			return;
		
		inputReleased(65535, e.getKey());
	}
	
	////////// INPUT ////////////

	private boolean pressed;
	
	public abstract void inputPressed(int key, int button);

	public abstract void inputReleased(int key, int button);

}
