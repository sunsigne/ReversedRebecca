package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DeleteRequest implements Request {

	////////// REQUEST ////////////

	public DeleteRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new DeleteRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "DELETE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		// determinate the position

		String pos = String.valueOf(target);
		int x = Integer.parseInt(pos.split("-")[0]);
		int y = Integer.parseInt(pos.split("-")[1]);
		GoalObject goal = new GoalObject(x, y, false);

		// remove the object

		Handler handler = object.getHandler();
		handler.removeObject(Handler.getObjectAtPos(handler, goal.getX(), goal.getY(), Size.M));
	}

}
