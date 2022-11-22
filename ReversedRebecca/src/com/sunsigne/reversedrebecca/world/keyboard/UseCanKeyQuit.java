package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.world.World;

public class UseCanKeyQuit extends WorldKeyboard {

	private static WorldKeyboard worldKeyboard = new UseCanKeyQuit();

	@Override
	public WorldKeyboard getWorldKeyboard() {
		return worldKeyboard;
	}

	//////////KEYBOARD ////////////
	
	@Override
	public void keyPressed(int key) {
		if (key != KeyEvent.VK_ESCAPE)
			return;
	
		if (LAYER.MENU.getHandler().getList().isEmpty())
			loadTitleScreen();
		else
			new Conductor().stopApp();
	}
	
	@Override
	public void keyReleased(int key) {
	
	}
	
	private void loadTitleScreen() {
		LAYER.LOADING.addObject(new LoadingScreen());
	
		World.get().destroy();
		new TitleScreen();
	
		LAYER.LOADING.getHandler().clear();
	}

}
