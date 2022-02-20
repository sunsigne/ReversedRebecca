package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.behaviors;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.foe.Foe;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.Size;

public class GoalIsPlayer implements TickBehavior {

	public GoalIsPlayer(Foe foe) {
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
		if (!canFollowPlayer()) {
			foe.setGoal(null);
			return;
		}

		Player player = new PlayerFinder().getPlayer();
		foe.setGoal(player);

	}

	private boolean canFollowPlayer() {

		// player is dead
		if (PlayerHealth.getInstance().isDead())
			return false;

		// foe and player are not not on the same layer
		if (foe.getHandler() != new PlayerFinder().getPlayer().getHandler())
			return false;

		return true;
	}

	@Deprecated
	private boolean canFollowPlayer(Player player) {

		// player is too far
		if (new PlayerFinder().isPlayerFutherThan(foe, 19 * Size.XS))
			return false;

		return true;
	}

	@Deprecated
	private void FollowPlayer(Player player) {
		float diffX = foe.getX() - player.getX();
		float diffY = foe.getY() - player.getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		foe.setVelX(foe.speed * Math.round((-1 / distance) * diffX));
		foe.setVelY(foe.speed * Math.round((-1 / distance) * diffY));
	}

}