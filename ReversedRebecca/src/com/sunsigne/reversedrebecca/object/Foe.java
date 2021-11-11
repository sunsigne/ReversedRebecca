package com.sunsigne.reversedrebecca.object;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;

public class Foe extends GameObject implements CollisionDetector, CollisionReactor {

	public static int speed = 6;
	private Player player;
	
	public Foe(Player player, int x, int y) {
		super(x, y);
		this.player = player;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		movingtoPlayer();
	}

	private void movingtoPlayer() {
		float diffX = getX() - player.getX();
		float diffY = getY() - player.getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		setVelX(speed * Math.round((-1 / distance) * diffX));
		setVelY(speed * Math.round((-1 / distance) * diffY));
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		g.setColor(Color.MAGENTA);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	////////// COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPass(detectorObject);
	}

}
