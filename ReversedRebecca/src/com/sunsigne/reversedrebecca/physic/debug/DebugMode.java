package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;

public abstract class DebugMode extends Cycloid<Boolean> implements KeyboardEvent, PhysicLaw {

	public DebugMode() {
		super(false, true);
	}

	////////// DEBUG MODE ////////////

	public abstract int getIndex();

	protected abstract void actionWhenTurnedOn();

	protected abstract void actionWhenTurnedOff();

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	protected abstract int getKeyEvent();

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == getKeyEvent()) {
			cycle();
			if (getState())
				actionWhenTurnedOn();
			else
				actionWhenTurnedOff();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
