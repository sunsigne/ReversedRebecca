package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.MenuIngameScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.menu.submenu.ingame.ResumeScreen;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.world.World;

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
	
		if(menu == null) {
			if (LAYER.MENU.getHandler().getList().isEmpty())
				loadResumeScreen();
		}
		
		else
			unloadResumeScreen();
	}
	
	@Override
	public void keyReleased(int key) {
	
	}
	
	private MenuIngameScreen menu;
		
	private void loadResumeScreen() {
		World.get().freeze(true);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button");
		menu = new ResumeScreen();
	}
	
	private void unloadResumeScreen() {
		World.get().freeze(false);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button_back");
		LAYER.MENU.getHandler().clear();
		menu = null;
	}

}
