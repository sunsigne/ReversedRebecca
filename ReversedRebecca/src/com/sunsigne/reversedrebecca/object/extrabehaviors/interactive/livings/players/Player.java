package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.CameraFollowing;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.CanInteract;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.DieWhenNoHp;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.HasInvulnerabilityFrame;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.HasPlayerSpeed;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.behaviors.UserCanKeyMove;

public class Player extends LivingObject {

	public Player(int x, int y) {
		super("PLAYER", x, y, null);
		addPlayerBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior cameraFollowing;
	public Behavior hasInvulnerabilityFrame;
	public Behavior dieWhenNoHp;
	public Behavior userCanKeyMove;
	public Behavior hasPlayerSpeed;
	public Behavior canInteract;

	private void addPlayerBehaviors() {

		cameraFollowing = new CameraFollowing(this);
		addBehavior(cameraFollowing);

		hasInvulnerabilityFrame = new HasInvulnerabilityFrame(this);
		addBehavior(hasInvulnerabilityFrame);

		dieWhenNoHp = new DieWhenNoHp(this);
		addBehavior(dieWhenNoHp);

		userCanKeyMove = new UserCanKeyMove(this);
		addBehavior(userCanKeyMove);

		hasPlayerSpeed = new HasPlayerSpeed(this);
		addBehavior(hasPlayerSpeed);

		canInteract = new CanInteract(this);
		addBehavior(canInteract);
	}

	@Override
	public Behavior[] behaviorToPauseIfStunned() {
		return new Behavior[] { canInteract };
		// userCanKeyMove is already "paused" due to the surVelocity
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
		// it depends on context ! But "true" corrupts PathFinding.
	}

}
