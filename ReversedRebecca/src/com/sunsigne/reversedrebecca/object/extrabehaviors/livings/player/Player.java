package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public class Player extends LivingObject implements CollisionDetector, CollisionReactor {

	public int speed = 10;
	
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
