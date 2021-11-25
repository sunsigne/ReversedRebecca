package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.pattern.PlayerFinder;

public class Foe extends LivingObject {

	public int speed = 6;
	
	public Foe(String name, int x, int y) {
		super(name ,x, y);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		movingtoPlayer();
	}

	private void movingtoPlayer() {
		Player player = new PlayerFinder().getPlayer();
		if(player == null)
			return;
		
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
