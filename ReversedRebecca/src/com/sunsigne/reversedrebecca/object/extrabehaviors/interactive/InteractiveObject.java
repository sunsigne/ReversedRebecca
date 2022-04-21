package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.behaviors.BlockingPath;

public class InteractiveObject extends ExtraBehaviorsObject {

	public InteractiveObject(String name, int x, int y) {
		super(name, x, y);
		addBasicBehaviors();
	}

	////////// BEHAVIOR ////////////

	public Behavior blockingPath;

	private void addBasicBehaviors() {

		blockingPath = new BlockingPath(this);
		addBehavior(blockingPath);
	}

}
