package com.sunsigne.reversedrebecca.world.controllers;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;

public class UserCanInputMenu extends WorldControllers {

	private static WorldControllers worldKeyboard = new UserCanInputMenu();

	@Override
	public WorldControllers getWorldControllers() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public void inputPressed(int key, int button) {
		if (key != KeyEvent.VK_ESCAPE && button != ButtonEvent.START)
			return;

		if (MenuIngameController.getMenu() == null) {
			if (LAYER.MENU.getHandler().getList().isEmpty())
				new MenuIngameController().loadResumeScreen();
		}

		else {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "button_back");
			new MenuIngameController().unloadResumeScreen();
		}
	}

	@Override
	public void inputReleased(int key, int button) {

	}

}
