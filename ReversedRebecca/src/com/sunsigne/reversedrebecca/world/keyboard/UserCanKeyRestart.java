package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.world.World;

public class UserCanKeyRestart extends WorldKeyboard {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_R) {
			String lvl = World.get().getLevelName();
			new World(lvl);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
