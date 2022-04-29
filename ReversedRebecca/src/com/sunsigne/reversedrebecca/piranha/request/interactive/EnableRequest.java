package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class EnableRequest implements Request {

	////////// REQUEST ////////////

	public EnableRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new EnableRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "ENABLE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	public boolean disable() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// determinate the position

		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);
		GoalObject goal = new GoalObject(x, y, false);

		// determinate the type of object

		GameObject gameOject = Handler.getObjectAtPos(object.getHandler(), goal.getX(), goal.getY(), object.getSize());
		if (gameOject instanceof Interactive == false)
			return;

		Interactive interactive = (Interactive) gameOject;
		interactive.setDisabled(disable());
	}

}
