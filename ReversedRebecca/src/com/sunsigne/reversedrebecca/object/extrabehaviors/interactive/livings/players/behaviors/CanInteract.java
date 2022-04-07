package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;

public class CanInteract implements Behavior {

	public CanInteract(Player player) {
		this.player = player;
	}

	////////// BEHAVIOR ////////////

	private Player player;

	@Override
	public Player getExtraBehaviorsObject() {
		return player;
	}

}
