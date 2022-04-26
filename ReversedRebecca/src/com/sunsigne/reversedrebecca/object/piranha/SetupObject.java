package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.system.Size;

public class SetupObject extends PiranhaObject {

	public SetupObject() {
		super("set-up", -Size.M, -Size.M);
		setDisabled(true);
	}

	////////// PATH FINDER ////////////

	@Override
	public boolean mustFollowPath() {
		return false;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {

	}

}
