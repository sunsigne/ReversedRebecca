package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.DieWhenNoHp;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.HasInvulnerabilityFrame;

public class Player extends LivingObject {

	public Player(int x, int y) {
		super("PLAYER", x, y, null);
		addPlayerBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior hasInvulnerabilityFrame;
	public Behavior dieWhenNoHp;

	private void addPlayerBehaviors() {

		hasInvulnerabilityFrame = new HasInvulnerabilityFrame(this);
		addBehavior(hasInvulnerabilityFrame);

		dieWhenNoHp = new DieWhenNoHp(this);
		addBehavior(dieWhenNoHp);

	}

	@Override
	public Behavior[] behaviorToPauseIfStunned() {
		return new Behavior[] { /*canInteract*/ };
		// userCanKeyMove is already "paused" due to the surVelocity
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
		// it depends on context ! But "true" corrupts PathFinding.
	}

	@Override
	public boolean mustFollowPath() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setMustFollowPath(boolean mustFollowPath) {
		// TODO Auto-generated method stub
		
	}

}
