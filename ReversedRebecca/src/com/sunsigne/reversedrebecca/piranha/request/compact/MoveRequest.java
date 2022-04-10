package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.npc.NPC;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.Request;

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
	
	public boolean isRunning() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		if (object instanceof NPC)
			((NPC) object).setRunning(isRunning());
		object.setGoal(goal);
	}

}
