package com.sunsigne.reversedrebecca.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.gui.ExempleHP;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.Texture;
import com.sunsigne.reversedrebecca.system.OldConductor;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class World implements Updatable, KeyboardEvent {

	public World(String level_name) {
		this.level_name = level_name;
		createTexture();
		LAYER.GROUND.addObject(this);
	}

	private String level_name;

	////////// ??? ////////////

	public void run() {
		new MapCreator().loadLevel(world_image);

		LAYER.GUI.addObject(new ExempleHP());
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage ground_image;
	private BufferedImage world_image;


	public void createTexture() {
		ground_image = new ImageTask().loadImage("maps/" + level_name + "/ground" + ".png");
		world_image = new ImageTask().loadImage("maps/" + level_name + "/world" + ".png");
	}


	public BufferedImage getImage() {
		return ground_image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int pixel = 16;
		g.drawImage(getImage(), 0, 0, getImage().getWidth() * Size.M / pixel, getImage().getHeight() * Size.M / pixel,
				null);
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
			LAYER.WORLD.getHandler().clear();
			destroyControls();
			new TitleScreen();
		}

		if (key == KeyEvent.VK_R) {
			LAYER.WORLD.getHandler().clear();
			run();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
