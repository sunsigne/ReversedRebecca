package com.sunsigne.reversedrebecca.menu.ingame;

import com.sunsigne.reversedrebecca.menu.ingame.submenu.ResumeScreen;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
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
		hardFreeze(true);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button");
		menu = new ResumeScreen();
	}

	public void unloadResumeScreen() {
		hardFreeze(false);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button_back");
		LAYER.MENU.getHandler().clear();
		menu = null;
	}

	public void hardFreeze(boolean freeze) {
		for (LAYER tempLayer : LAYER.values())
			tempLayer.getHandler().setFreezeTicking(freeze);
		
		if(!freeze && World.get().isFrozen())
			World.get().freeze(true);

		new PlayerFinder().setPlayerCanInterract(!freeze);

		if (freeze) // remove fading menu if froze before completed
			((FadeMenuLaw) PhysicList.getList().getObject(new FadeMenuLaw())).setFading(false);
	}

}
