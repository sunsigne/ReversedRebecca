package com.sunsigne.reversedrebecca.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.OldConductor;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class World implements Updatable, KeyboardEvent {

	////////// SIGNELTON ////////////

	private World() {
		LAYER.GROUND.addObject(this);
	}

	private static World instance = null;

	public static World getInstance() {
		if (instance == null)
			instance = new World();
		return instance;
	}

	////////// ??? ////////////

	public void run() {
		BufferedImage lvl001 = new ImageTask().loadImage("levels/lvl001/rgb_ground.png");
		new MapCreator().loadLevel(lvl001);
		
		map = new ImageTask().loadImage("levels/lvl001/map_ground.png");
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	private BufferedImage map;

	@Override
	public void render(Graphics g) {
		if (map != null)
			g.drawImage(map, 0, 0, map.getWidth() * 6, map.getHeight() * 6, null);
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

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
