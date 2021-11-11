package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.behaviors.CameraFollowing;
import com.sunsigne.reversedrebecca.object.behaviors.UserCanKeyMove;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;

public class Player extends LivingObject implements CollisionDetector, CollisionReactor {

	public Player(int x, int y) {
		this("Rebecca", x, y);
	}
	
	public Player(String name, int x, int y) {
		super(name, x, y);
		addPlayerBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior userCanKeyMove = new UserCanKeyMove(this);
	public Behavior cameraFollowing = new CameraFollowing(this);
		
	private void addPlayerBehaviors() {
		addBehavior(userCanKeyMove);
		addBehavior(cameraFollowing);
	}

}
