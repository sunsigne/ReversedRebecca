package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.CameraFollowing;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.CanInteract;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.PlayerHealth;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.UserCanKeyMove;
import com.sunsigne.reversedrebecca.system.Size;

public class Player extends LivingObject {

	public int speed = Size.XS/3;
	
	public Player(int x, int y) {
		this("Rebecca", x, y);
	}
	
	public Player(String name, int x, int y) {
		super(name, x, y);
		addPlayerBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior userCanKeyMove;
	public Behavior canInteract;
	public Behavior cameraFollowing;	
	public Behavior playerHealth;
	
	private void addPlayerBehaviors() {
		
		userCanKeyMove = new UserCanKeyMove(this);
		addBehavior(userCanKeyMove);
		
		canInteract = new CanInteract(this);
		addBehavior(canInteract);
		
		cameraFollowing = new CameraFollowing(this);
		addBehavior(cameraFollowing);
		
		playerHealth = new PlayerHealth(this);

	}
	
}
