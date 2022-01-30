package com.sunsigne.reversedrebecca.object.puzzler;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;

public class DoorObject extends PuzzlerObject {

	public DoorObject(DIFFICULTY difficulty, int x, int y) {
		super(difficulty, x, y);
	}
	
	////////// NAME ////////////

	@Override
	public String getName() {
		return "door";
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////
	
	// of course the final door mecanics is more complexe
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> getHandler().removeObject(this));
	}

}
