package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;

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
