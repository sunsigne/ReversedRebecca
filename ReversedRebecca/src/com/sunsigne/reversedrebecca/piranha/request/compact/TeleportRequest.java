package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.Request;

public class TeleportRequest implements Request {

	////////// REQUEST ////////////

	public TeleportRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new TeleportRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "TP";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		object.setX(goal.getX());
		object.setY(goal.getY());
	}

}
