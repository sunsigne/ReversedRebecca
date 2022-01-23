package com.sunsigne.reversedrebecca.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.world.behaviors.WorldBehavior;
import com.sunsigne.reversedrebecca.world.behaviors.WorldKeyboardBehavior;
import com.sunsigne.reversedrebecca.world.behaviors.WorldRenderBehavior;
import com.sunsigne.reversedrebecca.world.behaviors.WorldTickBehavior;

public class ExtraBehaviorsWorld extends SuperExtraBehaviorsWorld
		implements WorldBehavior, WorldTickBehavior, WorldRenderBehavior, WorldKeyboardBehavior {

	public ExtraBehaviorsWorld(String levelName) {
		this(levelName, LAYER.GROUND);
	}

	public ExtraBehaviorsWorld(String levelName, LAYER layer) {
		super(levelName, layer);
	}

	////////// MAP OR LIST ////////////

	private GameLimitedList<WorldBehavior> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public GameLimitedList<WorldBehavior> getBehaviorList() {
		return list;
	}

	public void addBehavior(WorldBehavior behavior) {
		getBehaviorList().addObject(behavior);
	}

	public void removeBehavior(WorldBehavior behavior) {
		getBehaviorList().removeObject(behavior);
	}

	////////// BEHAVIOR ////////////

	@Override
	public ExtraBehaviorsWorld getExtraBehaviorsWorld() {
		return this;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		for (WorldBehavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof WorldTickBehavior) {
					WorldTickBehavior tempTickBehavior = (WorldTickBehavior) tempBehavior;
					tempTickBehavior.tick();
				}
			}
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		for (WorldBehavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof WorldRenderBehavior) {
					WorldRenderBehavior tempRenderBehavior = (WorldRenderBehavior) tempBehavior;
					tempRenderBehavior.render(g);
				}
			}
		}
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (WorldBehavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof WorldKeyboardBehavior) {
					WorldKeyboardBehavior tempKeyboardBehavior = (WorldKeyboardBehavior) tempBehavior;
					tempKeyboardBehavior.keyPressed(e);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (WorldBehavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof WorldKeyboardBehavior) {
					WorldKeyboardBehavior tempKeyboardBehavior = (WorldKeyboardBehavior) tempBehavior;
					tempKeyboardBehavior.keyReleased(e);
				}
			}
		}
	}

}
