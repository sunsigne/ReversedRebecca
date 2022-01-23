package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.system.OldConductor;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.behaviors.WorldKeyboardBehavior;

public class UseCanKeyQuit implements WorldKeyboardBehavior {

	public UseCanKeyQuit(World world) {
		this.world = world;
	}

	////////// BEHAVIOR ////////////

	private World world;

	@Override
	public World getExtraBehaviorsWorld() {
		return world;
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE)
			new OldConductor().stopApp();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
