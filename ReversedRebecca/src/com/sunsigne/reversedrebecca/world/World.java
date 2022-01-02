package com.sunsigne.reversedrebecca.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.unchecked.system.OldConductor;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class World implements KeyboardEvent {

	////////// SIGNELTON ////////////

	private World() {

	}

	private static World instance = null;

	public static World getInstance() {
		if (instance == null)
			instance = new World();
		return instance;
	}

	////////// ??? ////////////

	public void run() {
		BufferedImage lvl001 = new ImageTask().loadImage("levels/lvl001/lvl001_mapping.png");
		new MapCreator().loadLevel(lvl001);
	}
		
	////////// KEYBOARD ////////////

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
