package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.system.Conductor;

public class UseCanKeyQuit extends WorldKeyboard {

	private static WorldKeyboard worldKeyboard = new UseCanKeyQuit();

	@Override
	public WorldKeyboard getWorldKeyboard() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_ESCAPE)
			new Conductor().stopApp();
	}

	@Override
	public void keyReleased(int key) {

	}

}
