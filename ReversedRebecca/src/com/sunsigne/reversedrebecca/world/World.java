package com.sunsigne.reversedrebecca.world;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.unchecked.system.OldConductor;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class World implements KeyboardEvent {

	
	public void run() {
		BufferedImage lvl001 = new ImageTask().loadImage("maps/$working/lvl001_mapping.png");
		new MapCreator().loadLevel(lvl001);
	}
	
	//////////KEYBOARD ////////////
	
	KeyboardController keyboardController = new KeyboardController(this);
	
	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE)
			new OldConductor().stopApp();
	
		if (key == KeyEvent.VK_R) {
			LAYER.WORLD.getHandler().clear();
			run();
		}			
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	}

}
