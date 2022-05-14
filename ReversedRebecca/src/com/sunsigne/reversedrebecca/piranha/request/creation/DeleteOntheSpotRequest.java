package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class DeleteOntheSpotRequest extends DeleteRequest {

	////////// REQUEST ////////////

	public DeleteOntheSpotRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new DeleteOntheSpotRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "DELETE_ONTHESPOT";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		GoalObject goal = new GoalObject(object.getX(), object.getY(), true);
		String formatedTarget = goal.getX() + "-" + goal.getY() + ":" + target;
		super.doAction(object, formatedTarget);
	}

}
