package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.pattern.PlayerFinder;

public class MovingToPlayer implements Behavior {

	public MovingToPlayer(Foe foe) {
		this.foe = foe;
	}

	////////// BEHAVIOR ////////////

	private Foe foe;

	@Override
	public Foe getExtraBehaviorsObject() {
		return foe;
	}

	////////// TICK ////////////

	private Player player;
	
	@Override
	public void tick() {
		if(player == null) initPlayer();
		else FollowPlayer();
	}
	
	private void initPlayer() {
		player = new PlayerFinder().getPlayer();
	}
	
	private void FollowPlayer() {
		float diffX = foe.getX() - player.getX();
		float diffY = foe.getY() - player.getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		foe.setVelX(foe.speed * Math.round((-1 / distance) * diffX));
		foe.setVelY(foe.speed * Math.round((-1 / distance) * diffY));
	}

	////////// RENDER ////////////


	@Override
	public void render(Graphics g) {

	}
	
}