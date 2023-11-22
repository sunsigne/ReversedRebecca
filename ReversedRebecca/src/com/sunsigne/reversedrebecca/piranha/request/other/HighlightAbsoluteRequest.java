package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.InteractiveObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class HighlightAbsoluteRequest implements Request {

	////////// REQUEST ////////////

	public HighlightAbsoluteRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new HighlightAbsoluteRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "HIGHLIGHT_ABSOLUTE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	public int[] getRelativePos(PiranhaObject object) {
		GoalObject goal = new GoalObject(object.getX(), object.getY(), true);
		int[] pos = { goal.getX(), goal.getY() };
		return pos;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (object instanceof InteractiveObject == false)
			return;

		String[] targets = target.split(",");
		int x = Integer.valueOf(targets[0]);
		int y = Integer.valueOf(targets[1]);
		int w = Integer.valueOf(targets[2]);
		int h = Integer.valueOf(targets[3]);

		int[] relative_pos = getRelativePos(object);
		((InteractiveObject) object).setHighlightRect(x - relative_pos[0], y - relative_pos[1], w, h);
	}

}
