package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.CameraFollowing;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.UserCanKeyMove;

public class Player extends LivingObject {

	public int speed = 10;
	
	public Player(int x, int y) {
		this("Rebecca", x, y);
	}
	
	public Player(String name, int x, int y) {
		super(name, x, y);
		addPlayerBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior userCanKeyMove;
	public Behavior cameraFollowing;	
	
	private void addPlayerBehaviors() {
		
		userCanKeyMove = new UserCanKeyMove(this);
		addBehavior(userCanKeyMove);
		
		cameraFollowing = new CameraFollowing(this);
		addBehavior(cameraFollowing);
	}
	
}
