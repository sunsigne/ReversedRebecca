package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.gui.DebugModeObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;

public abstract class DebugMode extends Cycloid<Boolean> implements PhysicLaw, KeyboardEvent {

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

	public abstract DebugMode getDebugMode();
	
	////////// NAME ////////////

	public abstract String getName();

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	// those are the keys F1, F2, etc.
	protected int getKeyEvent() {
		return 0x70 + local_index;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == getKeyEvent())
			cycle();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
