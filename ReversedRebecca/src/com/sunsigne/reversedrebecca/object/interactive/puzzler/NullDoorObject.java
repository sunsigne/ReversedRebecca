package com.sunsigne.reversedrebecca.object.interactive.puzzler;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;

public class NullDoorObject extends DoorObject {

	public NullDoorObject(int x, int y) {
		super(DIFFICULTY.NULL, x, y);
		setDisabled(true);
	}
	
	////////// RENDER ////////////
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}
	
	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> getHandler().removeObject(this));
	}

}
