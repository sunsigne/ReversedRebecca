package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.CollisionBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.pattern.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.TilePos;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.compact.FacingRequest;
import com.sunsigne.reversedrebecca.system.Size;

public class StopWhenMeetPlayer implements CollisionBehavior {

	public StopWhenMeetPlayer(LivingObject living) {
		this.living = living;
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

	private boolean stunned;

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (!stunned)
			if (detectorObject instanceof Player) {
				stunObject();
//				shiftObject();
			}
		blockPath(detectorObject);
	}

	private void shiftObject() {
		setX(new TilePos().getTilePos(getX(), Size.M));
		setY(new TilePos().getTilePos(getY(), Size.M));
	}

	private void stunObject() {
		stunned = true;
		living.setMotionless();
		
		ConditionalListener listener = getPlayerDistanceListener(living, 3);
		living.addBehavior(new WaitforBehavior(living, listener));
		pauseBehaviors(true);
	}

	private void pauseBehaviors(boolean pause) {

		Behavior[] behaviorToPause = living.behaviorToPauseIfStunned();
		for (int index = 0; index < behaviorToPause.length; index++) {
			if (pause)
				living.removeBehavior(behaviorToPause[index]);
			else
				living.addBehavior(behaviorToPause[index]);
		}

	}

	private ConditionalListener getPlayerDistanceListener(ExtraBehaviorsObject object, int distance) {

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				Request request = RequestList.getList().getObject(new FacingRequest());
				request.doAction(living, "player");
				return new PlayerFinder().isPlayerFutherThan(object, distance);
			}

			@Override
			public void doAction() {
				stunned = false;
				pauseBehaviors(false);
			}
		};
	}

}