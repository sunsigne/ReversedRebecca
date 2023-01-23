package com.sunsigne.reversedrebecca.menu.ingame;

import java.awt.Cursor;

import com.sunsigne.reversedrebecca.menu.ingame.submenu.ResumeScreen;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.World;

public class MenuIngameController {

	////////// MENU ////////////

	private static ResumeScreen menu;

	public static ResumeScreen getMenu() {
		return menu;
	}

	private static Cursor cursor;
	
	public void loadResumeScreen() {
		hardFreeze(true);
		
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button");
		menu = new ResumeScreen();
		
		cursor = Game.getInstance().getCursor();
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
	}

	public void unloadResumeScreen() {
		hardFreeze(false);
		
		LAYER.MENU.getHandler().clear();
		menu = null;
		
		Game.getInstance().setCursor(cursor);
	}

	public void hardFreeze(boolean freeze) {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer == LAYER.MENU)
				break;

			tempLayer.getHandler().setFreezeTicking(freeze);
		}

		if (!freeze && World.get().isFrozen())
			World.get().freeze(true);
		else
			new PlayerFinder().setPlayerCanInterract(!freeze);
	}

}
