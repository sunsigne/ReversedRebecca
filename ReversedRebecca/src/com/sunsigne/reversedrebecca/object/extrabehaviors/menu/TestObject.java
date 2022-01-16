package com.sunsigne.reversedrebecca.object.extrabehaviors.menu;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;

public class TestObject extends ExtraBehaviorsObject {

	public TestObject(int x, int y, int w, int h) {
		super(x, y, w, h);
		addClickableBehaviors();
	}

	////////// BEHAVIOR ////////////
	
	public Behavior testBehavior;

	private void addClickableBehaviors() {
		
		testBehavior = new TestBehavior(this);
		addBehavior(testBehavior);
	}	

}
