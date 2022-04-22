package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.HasInvulnerabilityFrame;

public class Player extends LivingObject {

	public Player(int x, int y) {
		super("PLAYER", x, y, null);
		addPlayerBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior hasInvulnerabilityFrame;

	private void addPlayerBehaviors() {

		hasInvulnerabilityFrame = new HasInvulnerabilityFrame(this);
		addBehavior(hasInvulnerabilityFrame);

	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
		// it depends on context ! But "true" corrupts PathFinding.
	}

}
