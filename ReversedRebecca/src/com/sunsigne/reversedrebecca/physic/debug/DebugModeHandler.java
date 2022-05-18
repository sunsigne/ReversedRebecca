package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.gui.DebugModeObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class DebugModeHandler implements KeyboardEvent {

	public DebugModeHandler() {
		debugKeyEvent[0] = KeyEvent.VK_CONTROL;
		debugKeyEvent[1] = KeyEvent.VK_SHIFT;
		debugKeyEvent[2] = KeyEvent.VK_F1;
	}

	////////// USEFUL ////////////

	private Cycloid<Boolean> debug = new Cycloid<>(false, true);

	private void updateDebugObjects() {
		if (debug.getState())
			addDebugObjects();
		else
			removeDebugObjects();
	}

	private void addDebugObjects() {
		for (PhysicLaw tempLaw : PhysicList.getList().getList()) {
			if (tempLaw instanceof DebugMode == false)
				continue;

			DebugMode tempDebug = (DebugMode) tempLaw;
			LAYER.DEBUG.addObject(new DebugModeObject(tempDebug));
		}
	}

	private void removeDebugObjects() {
		var list = new GameList<Updatable>(LISTTYPE.LINKED);

		for (Updatable tempUpdatable : LAYER.DEBUG.getHandler().getList()) {
			if (tempUpdatable instanceof DebugModeObject)
				list.addObject(tempUpdatable);
		}

		for (Updatable tempUpdatable : list.getList()) {
			LAYER.DEBUG.getHandler().removeObject(tempUpdatable);
		}
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	private int[] debugKeyEvent = new int[3];
	private boolean[] debugKeyPressed = new boolean[3];

	private void debugKey(int key, boolean pressed) {
		for (int index = 0; index < 3; index++) {
			if (key == debugKeyEvent[index])
				debugKeyPressed[index] = pressed;
		}
	}

	private boolean debugCombination() {
		for (int index = 0; index < 3; index++) {
			if (debugKeyPressed[index] == false)
				return false;
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		debugKey(key, true);

		if (debugCombination()) {
			debug.cycle();
			updateDebugObjects();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		debugKey(key, false);
	}

}
