package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;

public class CreateDecorationRequest implements Request {

	////////// REQUEST ////////////

	public CreateDecorationRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CreateDecorationRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CREATE_DECORATION";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// determinate the position

		String pos = String.valueOf(target.split(":")[0]);
		boolean onTheSpot = pos.equalsIgnoreCase("onthespot");
		int x = onTheSpot ? (object.getX() / Size.M) : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? (object.getY() / Size.M) : Integer.parseInt(pos.split("-")[1]);
		
		GoalObject goal = new GoalObject(x, y, false);

		// determinate the size and the name

		String data = String.valueOf(target.split(":")[1]);
		int width = Integer.parseInt(data.split(",")[0]);
		int height = Integer.parseInt(data.split(",")[1]);
		String name = String.valueOf(data.split(",")[2]).toLowerCase();

		// creation of the object

		GameObject creation = new DecorationObject(goal.getX(), goal.getY(), width * Size.M, height * Size.M, name);
		object.getHandler().getList().add(0, creation);
	}

}
