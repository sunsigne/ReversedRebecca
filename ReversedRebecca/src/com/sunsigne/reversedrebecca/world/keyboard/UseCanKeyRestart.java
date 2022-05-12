package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.world.World;

public class UseCanKeyRestart extends WorldKeyboard {

	private static WorldKeyboard worldKeyboard = new UseCanKeyRestart();

	@Override
	public WorldKeyboard getWorldKeyboard() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_BACK_SPACE) {
			new World("lvl000");
		}
	}

	@Override
	public void keyReleased(int key) {

	}

}
