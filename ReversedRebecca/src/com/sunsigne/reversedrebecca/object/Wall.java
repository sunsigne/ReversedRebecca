package com.sunsigne.reversedrebecca.object;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;

public class Wall extends GameObject implements CollisionReactor {

	public Wall(int x, int y) {
		super(x, y);
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
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPass(detectorObject);
	}

}
