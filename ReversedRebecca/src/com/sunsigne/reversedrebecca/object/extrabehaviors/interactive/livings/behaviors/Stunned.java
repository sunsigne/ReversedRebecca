package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors;

import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;

public class Stunned implements TickBehavior {

	public Stunned(LivingObject living) {
		this(living, 30);
	}

	public Stunned(LivingObject living, int stun_time) {
		this.living = living;
		this.time = stun_time;
		this.behaviorsToPause = living.behaviorToPauseIfStunned();

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

	private int time;

	@Override
	public void tick() {
		time--;
		if (time <= 0)
			wakeUp();
	}

	private void wakeUp() {
		retablishBehaviors(behaviorsToPause);
		living.removeBehavior(this);
	}

}