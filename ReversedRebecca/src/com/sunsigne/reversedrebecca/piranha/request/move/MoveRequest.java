package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.LookAtPlayer;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class MoveRequest implements Request {

	////////// REQUEST ////////////

	public MoveRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new MoveRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "MOVE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.NORMAL;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		if (target.equalsIgnoreCase("stop")) {
			object.setGoal(null);
			return;
		}

		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		if (object instanceof SpeedVariator) {
			SpeedVariator variator = (SpeedVariator) object;
			variator.setSpeedness(getSpeedness());
		}

		if (object instanceof LivingObject) {
			LivingObject living = (LivingObject) object;
			if (object instanceof Player == false)
				living.removeBehavior(living.getBehaviorList().getObject(new LookAtPlayer(living)));
		}

		object.setGoal(goal);
	}

}
