package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.CollisionBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class StopWhenMeetPlayer implements CollisionBehavior {

	public StopWhenMeetPlayer(LivingObject living) {
		this.living = living;
		lookAtThePlayer = new LookAtPlayer(living);
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return living;
	}

	////////// POSITION ////////////

	@Override
	public int getX() {
		return getExtraBehaviorsObject().getX();
	}

	@Override
	public int getY() {
		return getExtraBehaviorsObject().getY();
	}

	@Override
	public void setX(int x) {
		getExtraBehaviorsObject().setX(x);
	}

	@Override
	public void setY(int y) {
		getExtraBehaviorsObject().setY(y);
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return getExtraBehaviorsObject().getWidth();
	}

	@Override
	public int getHeight() {
		return getExtraBehaviorsObject().getHeight();
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return getExtraBehaviorsObject().isBlockingSight();
	}

	@Override
	public boolean isBlockingPath() {
		return getExtraBehaviorsObject().isBlockingPath();
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (new PlayerFinder().isPlayerInvolved(detectorObject)) {
			paralyseObject();
			lookAtThePlayer();
		}
	}

	private void paralyseObject() {
		ConditionalListener listener = getPlayerDistanceListener(living, 3);
		living.addBehavior(new WaitforBehavior(living, listener));
	}

	private Behavior lookAtThePlayer;

	private void lookAtThePlayer() {
		living.addBehavior(lookAtThePlayer);
	}

	private ConditionalListener getPlayerDistanceListener(ExtraBehaviorsObject object, int distance) {

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				return new PlayerFinder().isPlayerFutherThan(object, distance);
			}

			@Override
			public void doAction() {
				living.removeBehavior(lookAtThePlayer);
			}
		};
	}

}