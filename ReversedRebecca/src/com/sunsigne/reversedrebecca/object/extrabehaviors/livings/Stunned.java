package com.sunsigne.reversedrebecca.object.extrabehaviors.livings;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.Behavior;

public class Stunned implements Behavior {

	public Stunned(LivingObject living, Behavior... behaviorsToPause) {
		this.living = living;
		this.behaviorsToPause = behaviorsToPause;

		breakBehaviors(behaviorsToPause);
		living.setMotionless();
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;
	private Behavior[] behaviorsToPause;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}

	private void breakBehaviors(Behavior... behaviorsToBreak) {
		int size = behaviorsToBreak.length;
		for (int index = 0; index < size; index++) {
			living.removeBehavior(behaviorsToBreak[index]);
		}
	}

	private void retablishBehaviors(Behavior... behaviorsToRetablish) {
		int size = behaviorsToRetablish.length;
		for (int index = 0; index < size; index++) {
			living.addBehavior(behaviorsToRetablish[index]);
		}
	}

	////////// TICK ////////////

	private final int STUNNING_TIME = 30;
	private int time = STUNNING_TIME;

	@Override
	public void tick() {
		time--;
		if (time < 0)
			wakeUp();
	}

	private void wakeUp() {
		retablishBehaviors(behaviorsToPause);
		living.removeBehavior(this);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

}