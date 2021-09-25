package com.sunsigne.reversedrebecca.object;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.collision.CollisionDetector;
import com.sunsigne.reversedrebecca.object.collision.ICollisionDetection;
import com.sunsigne.reversedrebecca.object.collision.ICollisionReaction;
import com.sunsigne.reversedrebecca.ressources.images.ImageBank;

public class Foe extends GameObject implements ICollisionDetection, ICollisionReaction {

	public static final int SPEED = 32 / 5;

	public Foe(int x, int y) {
		super(true, false, x, y);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
//		movingtoPlayer();
	}

	private void movingtoPlayer() {
		float diffX = x - Player.get().getX();
		float diffY = y - Player.get().getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		velX = SPEED * Math.round((-1 / distance) * diffX);
		velY = SPEED * Math.round((-1 / distance) * diffY);
	}

	
	////////// RENDER ////////////

	@Override
	public ImageBank getImageBank(int... index) {
		return null;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, w, h);
	}

	////////// COLLISION ////////////

	private CollisionDetector collisionDetector = new CollisionDetector(this);

	@Override
	public CollisionDetector getCollisionDetector() {
		return collisionDetector;
	}
	
	@Override
	public void collidingReaction(GameObject clnDetectorObject) {
		blockPass(clnDetectorObject, this);
	}
}
