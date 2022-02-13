package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.world.World;

public abstract class WorldKeyboard implements KeyboardEvent {

	public abstract WorldKeyboard getWorldKeyboard();

	private World world;

	private boolean worldIsNull() {
		if (world == null) {
			world = World.get();
			if (world == null)
				return true;
		}
		return false;
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	public abstract void keyPressed(int key);

	public abstract void keyReleased(int key);

	private boolean pressed;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (worldIsNull())
			return;

		if (pressed)
			return;
		
		int key = e.getKeyCode();
		keyPressed(key);
		pressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (worldIsNull())
			return;

		int key = e.getKeyCode();
		keyReleased(key);
		pressed = false;
	}

}
