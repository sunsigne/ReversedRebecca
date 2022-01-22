package com.sunsigne.reversedrebecca.world;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.gui.GuiHealth;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class World extends WorldHolder implements KeyboardEvent {

	public World(String level_name) {
		super(level_name);
		setLayer(layer);
	}

	private LAYER layer = LAYER.GROUND;

	private LAYER getLayer() {
		return layer;
	}

	public void setLayer(LAYER layer) {
		this.layer = layer;
		getHandler().softRemoveObject(this);
		layer.addObject(this);
	}

	////////// ??? ////////////

	public void run() {
		new MapCreator().loadLevel(getGameMap(LAYER.WORLD_CONTENT));

		LAYER.GUI.addObject(new GuiHealth());
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int pixel = 16;
		BufferedImage img = null;

		if (getLayer() == LAYER.UP_GROUND) {
			img = getGameMap(LAYER.GROUND).getImage();
			g.drawImage(img, 0, 0, img.getWidth() * Size.M / pixel, img.getHeight() * Size.M / pixel, null);
		}
		
		img = getGameMap(getLayer()).getImage();
		g.drawImage(img, 0, 0, img.getWidth() * Size.M / pixel, img.getHeight() * Size.M / pixel, null);
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
		if (key == KeyEvent.VK_ESCAPE) {
			LAYER.GROUND.getHandler().clear();
			LAYER.WORLD_CONTENT.getHandler().clear();
			destroyControls();
			new TitleScreen();
		}

		if (key == KeyEvent.VK_R) {
			LAYER.WORLD_CONTENT.getHandler().clear();
			run();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
