package com.sunsigne.reversedrebecca.object.piranha.living;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.piranha.living.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class Foe extends LivingObject {

	public Foe(String name, int x, int y) {
		super(name, x, y);
		setPushingDirection(PUSHING_DIRECTION.FACING_OF_PUSHER);
		setHurtWhenPushing(true);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		super.tick();

		PiranhaPlayer player = new PlayerFinder().getPlayer();

		if (!canFollowPlayer(player)) {
			setGoal(null);
			return;
		}

		setGoal(player);
	}

	private boolean canFollowPlayer(PiranhaPlayer player) {

		// player is dead
		if (PlayerHealth.getInstance().isDead())
			return false;

		// foe and player are not not on the same layer
		if (getHandler() != player.getHandler())
			return false;

		return true;
	}

	////////// COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof PiranhaPlayer)
			super.collidingReaction(detectorObject);
		else
			defaultCollindingReaction(detectorObject);
	}

}
