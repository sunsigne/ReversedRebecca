package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.DebugModeObject;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.world.LAYER;

public abstract class DebugMode extends Cycloid<Boolean> implements KeyboardEvent, PhysicLaw {

	public DebugMode() {
		super(false, true);
		PhysicList.getList().addObject(this);
		LAYER.DEBUG.addObject(new DebugModeObject(this));
		index++;
	}

	private static int index = 0;
	private int local_index = index;
	
	public int getIndex() {
		return local_index;
	}
	
	////////// DEBUG MODE ////////////

	public abstract String getName();
	
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
