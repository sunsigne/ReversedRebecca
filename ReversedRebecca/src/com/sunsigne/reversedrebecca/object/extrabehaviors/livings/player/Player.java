package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.CameraFollowing;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.CanInteract;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.DieWhenNoHp;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.HasInvulnerabilityFrame;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.UserCanKeyMove;
import com.sunsigne.reversedrebecca.system.Size;

public class Player extends LivingObject {

	public Player(int x, int y) {
		this("Rebecca", x, y);
	}

	public Player(String name, int x, int y) {
		super(name, x, y, Size.XS / 3);

		addPlayerBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior cameraFollowing;
	public Behavior hasInvulnerabilityFrame;
	public Behavior dieWhenNoHp;
	public Behavior userCanKeyMove;
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

		canInteract = new CanInteract(this);
		addBehavior(canInteract);
	}

}
