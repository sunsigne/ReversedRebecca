package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;

public class UseCanKeyDie extends WorldKeyboard {

	private static WorldKeyboard worldKeyboard = new UseCanKeyDie();

	@Override
	public WorldKeyboard getWorldKeyboard() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_EXCLAMATION_MARK)
			PlayerHealth.getInstance().setHp(0);
	}

	@Override
	public void keyReleased(int key) {

	}

}
