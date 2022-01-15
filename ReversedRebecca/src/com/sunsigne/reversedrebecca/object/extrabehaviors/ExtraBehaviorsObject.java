package com.sunsigne.reversedrebecca.object.extrabehaviors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.CollisionBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.KeyboardBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.RenderBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;

public abstract class ExtraBehaviorsObject extends SuperExtraBehaviorsObject
		implements Behavior, TickBehavior, RenderBehavior, CollisionBehavior, KeyboardBehavior {

	public ExtraBehaviorsObject(String name, int x, int y) {
		super(name, x, y);
	}

	////////// MAP OR LIST ////////////

	private GameLimitedList<Behavior> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public GameLimitedList<Behavior> getBehaviorList() {
		return list;
	}

	public void addBehavior(Behavior behavior) {
		getBehaviorList().addObject(behavior);
	}

	public void removeBehavior(Behavior behavior) {
		getBehaviorList().removeObject(behavior);
	}

	////////// BEHAVIOR ////////////

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return this;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof TickBehavior) {
					TickBehavior tempTickBehavior = (TickBehavior) tempBehavior;
					tempTickBehavior.tick();
				}
			}
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof RenderBehavior) {
					RenderBehavior tempRenderBehavior = (RenderBehavior) tempBehavior;
					tempRenderBehavior.render(g);
				}
			}
		}
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof CollisionBehavior) {
					CollisionBehavior tempCollisionBehavior = (CollisionBehavior) tempBehavior;
					tempCollisionBehavior.collidingReaction(detectorObject);
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
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof KeyboardBehavior) {
					KeyboardBehavior tempKeyboardBehavior = (KeyboardBehavior) tempBehavior;
					tempKeyboardBehavior.keyPressed(e);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (Behavior tempBehavior : getBehaviorList().getList()) {
			if (tempBehavior != null) {
				if (tempBehavior instanceof KeyboardBehavior) {
					KeyboardBehavior tempKeyboardBehavior = (KeyboardBehavior) tempBehavior;
					tempKeyboardBehavior.keyReleased(e);
				}
			}
		}
	}

}
