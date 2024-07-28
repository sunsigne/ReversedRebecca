package com.sunsigne.reversedrebecca.world.controllers;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.world.World;

public class UserCanInputRestart extends WorldControllers {

	private static WorldControllers worldKeyboard = new UserCanInputRestart();

	@Override
	public WorldControllers getWorldControllers() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////
	
	@Override
	public boolean devOnly() {
		return true;
	}
	
	@Override
	public void inputPressed(int key, int button) {
		if (key == KeyEvent.VK_BACK_SPACE) {
			new World(new Save().getLevel(false));
		}
	}

	@Override
	public void inputReleased(int key, int button) {

	}

}
