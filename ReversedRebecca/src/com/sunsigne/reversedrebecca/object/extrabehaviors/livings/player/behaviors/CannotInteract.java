package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;

public class CannotInteract implements TickBehavior {

	public CannotInteract(Player player) {
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

	}
	
}
