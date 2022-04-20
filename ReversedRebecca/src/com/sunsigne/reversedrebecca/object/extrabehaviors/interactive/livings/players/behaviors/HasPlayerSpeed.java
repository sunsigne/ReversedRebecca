package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;

public class HasPlayerSpeed implements TickBehavior {

	public HasPlayerSpeed(Player player) {
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
		player.setSpeedness(SPEEDNESS.PLAYER_SPEED);
	}

}
