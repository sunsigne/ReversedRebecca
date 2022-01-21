package com.sunsigne.reversedrebecca.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.gui.GuiHealth;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class World extends WorldHolder implements KeyboardEvent {

	public World(String level_name) {
		super(level_name);
		LAYER.GROUND.addObject(this);
	}


	////////// ??? ////////////

	public void run() {
		new MapCreator().loadLevel(getGameMap(LAYER.WORLD));

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
