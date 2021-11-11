package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.behaviors.CameraFollowing;
import com.sunsigne.reversedrebecca.object.behaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.behaviors.UserCanKeyMove;
import com.sunsigne.reversedrebecca.object.behaviors.WalkingRender;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;

public class Player extends ExtraBehaviorsObject implements CollisionDetector, CollisionReactor {

	public Player(int x, int y) {
		this("Rebecca", x, y);
	}
	
	public Player(String name, int x, int y) {
		super(name, x, y);
		addBasicBehaviors();
	}

	////////// BEHAVIOR ////////////
	
	public Behavior userCanKeyMove = new UserCanKeyMove(this);
	public Behavior cameraFollowing = new CameraFollowing(this);
	public Behavior walkingRender = new WalkingRender(this);
		
	private void addBasicBehaviors() {
		addBehavior(userCanKeyMove);
		addBehavior(cameraFollowing);
		addBehavior(walkingRender);
	}
	
	////////// COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPass(detectorObject);
	}
		
}
