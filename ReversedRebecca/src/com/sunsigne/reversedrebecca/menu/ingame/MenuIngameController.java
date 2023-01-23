package com.sunsigne.reversedrebecca.menu.ingame;

import com.sunsigne.reversedrebecca.menu.ingame.submenu.ResumeScreen;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.world.World;

public class MenuIngameController {

	////////// MENU ////////////
	
	private static ResumeScreen menu;

	public static ResumeScreen getMenu() {
		return menu;
	}
	
	public void loadResumeScreen() {
		World.get().freeze(true);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button");
		menu = new ResumeScreen();
	}
	
	public void unloadResumeScreen() {
		World.get().freeze(false);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button_back");
		LAYER.MENU.getHandler().clear();
		menu = null;
	}

}
