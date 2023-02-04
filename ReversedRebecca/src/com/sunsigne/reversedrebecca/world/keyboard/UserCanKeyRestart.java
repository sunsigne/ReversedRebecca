package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.world.World;

public class UserCanKeyRestart extends WorldKeyboard {

	private static WorldKeyboard worldKeyboard = new UserCanKeyRestart();

	@Override
	public WorldKeyboard getWorldKeyboard() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_BACK_SPACE) {
			new World(new Save().getLevel(false));
		}
	}

	@Override
	public void keyReleased(int key) {

	}

}
