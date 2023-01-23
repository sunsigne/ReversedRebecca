package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class UseCanKeyMenu extends WorldKeyboard {

	private static WorldKeyboard worldKeyboard = new UseCanKeyMenu();

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
		
		else
			new MenuIngameController().unloadResumeScreen();
	}
	
	@Override
	public void keyReleased(int key) {
	
	}
	
}
