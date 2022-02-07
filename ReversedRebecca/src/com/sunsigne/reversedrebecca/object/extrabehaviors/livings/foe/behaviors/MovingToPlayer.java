package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.Foe;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.Size;

public class MovingToPlayer implements TickBehavior {

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

	@Override
	public void tick() {
		Player player = new PlayerFinder().getPlayer();

		if (player == null)
			return;

		if (canFollowPlayer(player))
			FollowPlayer(player);

		else
			foe.setMotionless();
	}

	private boolean canFollowPlayer(Player player) {

		// foe and player are not not on the same layer
		if (foe.getHandler() != player.getHandler())
			return false;

		// player is too far
		if (new PlayerFinder().isPlayerFutherThan(foe, 19 * Size.XS))
			return false;
		
		if(new PlayerFinder().getPlayerHealth().getHp() <= 0)
			return false;

		return true;
	}

	private void FollowPlayer(Player player) {
		float diffX = foe.getX() - player.getX();
		float diffY = foe.getY() - player.getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		foe.setVelX(foe.speed * Math.round((-1 / distance) * diffX));
		foe.setVelY(foe.speed * Math.round((-1 / distance) * diffY));
	}

}