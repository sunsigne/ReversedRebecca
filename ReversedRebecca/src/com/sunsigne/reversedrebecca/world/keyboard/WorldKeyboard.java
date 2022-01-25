package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public abstract class WorldKeyboard implements Updatable, KeyboardEvent {

	public WorldKeyboard() {
		layer = LAYER.DEBUG;
		layer.addObject(this);
	}

	////////// TICK ////////////

	private World world;
	private LAYER layer;

	@Override
	public void tick() {
		if (world == null) {
			world = World.get();
			if (world == null) {
				getHandler().removeObject(this);
				return;
			}
		}

		if (layer != world.getLayer(false))
			registerNewLayer();
	}

	private void registerNewLayer() {
		getHandler().softRemoveObject(this);
		layer = world.getLayer(false);
		layer.addObject(this);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}
	
}
