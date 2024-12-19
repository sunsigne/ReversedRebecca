package com.sunsigne.reversedrebecca.object.piranha.living;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.state.FacingRequest;

public class NPC extends LivingObject implements PlayerAvoider {

	public NPC(String name, int x, int y) {
		super(name, x, y);
		setPlayerAvoiderType(AVOIDERTYPE.AROUND);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PIRANHA : NPC";

		var dirName = "NAME:" + getName().toUpperCase();
		var dirAvoider = "AVOIDER:" + getPlayerAvoiderType();

		GoalObject goal = null;
		goal = new GoalObject(getX(), getY(), true);
		var dirPos = "POS:" + goal.getX() + "-" + goal.getY();

		goal = null;
		if (getGoal() != null)
			goal = new GoalObject(getGoal().getX(), getGoal().getY(), true);
		var dirGoal = goal == null ? "N/A" : goal.getX() + "-" + goal.getY();

		return clazz + " : " + dirName + " / " + dirAvoider + " / " + dirPos + " / " + "GOAL:" + dirGoal;
	}

	////////// PLAYER AVOIDER ////////////

	private boolean playerBlockingAvoider;

	@Override
	public boolean isPlayerBlockingAvoider() {
		return playerBlockingAvoider;
	}

	@Override
	public void setPlayerBlockingAvoider(boolean playerBlockingAvoider) {
		this.playerBlockingAvoider = playerBlockingAvoider;
	}

	private AVOIDERTYPE avoiderType;

	@Override
	public AVOIDERTYPE getPlayerAvoiderType() {
		return avoiderType;
	}

	@Override
	public void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType) {
		this.avoiderType = playerAvoiderType;
		PlayerAvoider.super.setPlayerAvoiderType(playerAvoiderType);
	}

	////////// COLLISION ////////////

	@Override
	protected void defaultCollindingReaction(CollisionDetector detectorObject) {
		if (getPlayerAvoiderType() == AVOIDERTYPE.STOP) {
			if (detectorObject instanceof Player & !isStunned() & !Cutscene.isRunning())
				stopAndLookAtPlayer();
		}

		if (getPlayerAvoiderType() == AVOIDERTYPE.THROUGH && detectorObject instanceof Player)
			return;

		if (getPlayerAvoiderType() == AVOIDERTYPE.FORCE && detectorObject instanceof Player)
			((Player) detectorObject).setForcePushed(true);

		blockPath(detectorObject);
	}

	private void stopAndLookAtPlayer() {
		setStunned(true);
		ConditionalListener registeredWaitfor = getWaitfor();
		ConditionalListener listener = continue_walking_if_player_far_enough(this, registeredWaitfor);
		setWaitfor(listener);
	}

	private void lookAtPlayer() {
		Request request = new FacingRequest();
		request.doAction(this, "player");
	}

	private ConditionalListener continue_walking_if_player_far_enough(LivingObject object,
			ConditionalListener registeredWaitfor) {

		return new ConditionalListener() {

			Position registeredGoal = getGoal();

			@Override
			public boolean canDoAction() {
				lookAtPlayer();
				boolean playerFarEnough = new PlayerFinder().isPlayerFutherThan(object, 3);
				boolean newGoalEtablished = getGoal() != registeredGoal;
				boolean newPlayerAvoider = getPlayerAvoiderType() != AVOIDERTYPE.STOP;
				boolean cutscene = Cutscene.isRunning();
				return playerFarEnough | newGoalEtablished | newPlayerAvoider | cutscene;
			}

			GenericListener action = () -> {
				setStunned(false); // already happens because of WaitforLaw and LivingObject
				setWaitfor(registeredWaitfor); // waitfor usualy replaces previous one, but not in this case
			};

			@Override
			public GenericListener getAction() {
				return action;
			}
		};
	}

}
