package com.sunsigne.reversedrebecca.object.extrabehaviors;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.CollisionBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;

public abstract class ExtraBehaviorsObject extends SuperExtraBehaviorsObject
		implements Behavior, TickBehavior, CollisionBehavior {

	public ExtraBehaviorsObject(String name, int x, int y) {
		this(name, x, y, Size.M, Size.M);
	}

	public ExtraBehaviorsObject(String name, int x, int y, int w, int h) {
		super(name, x, y, w, h);
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

	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return true;
	}

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


	@Override
	public KeyboardController getKeyBoardController() {
		// TODO Auto-generated method stub
		return null;
	}

}
