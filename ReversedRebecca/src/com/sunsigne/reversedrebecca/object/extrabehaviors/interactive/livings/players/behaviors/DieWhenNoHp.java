package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;

public class DieWhenNoHp implements TickBehavior {

	public DieWhenNoHp(Player player) {
		this.player = player;
	}

	////////// BEHAVIOR ////////////

	private Player player;

	@Override
	public Player getExtraBehaviorsObject() {
		return player;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		int hp = PlayerHealth.getInstance().getHp();

		if (hp <= 0)
			kill();
	}

	private void kill() {
		player.removeBehavior(player.canInteract);
		player.removeBehavior(player.userCanKeyMove);
		player.setFacing(DIRECTION.NULL);
	}

}
