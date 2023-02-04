package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class UserCanKeyMenu extends WorldKeyboard {

	private static WorldKeyboard worldKeyboard = new UserCanKeyMenu();

	@Override
	public WorldKeyboard getWorldKeyboard() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(int key) {
		if (key != KeyEvent.VK_ESCAPE)
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
	public void keyReleased(int key) {

	}

}
