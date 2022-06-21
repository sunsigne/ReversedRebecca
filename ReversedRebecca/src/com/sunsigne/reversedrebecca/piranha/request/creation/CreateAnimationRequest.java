package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.other.AnimatedDecorationObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;

public class CreateAnimationRequest implements Request {

	////////// REQUEST ////////////

	public CreateAnimationRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CreateAnimationRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CREATE_ANIMATION";
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

		// determine the animation characteristics

		int frames = Integer.parseInt(data.split(",")[3]);
		int animation_time = Integer.parseInt(data.split(",")[4]);
		boolean cycle = Boolean.parseBoolean(data.split(",")[5]);

		// creation of the object

		GameObject creation = new AnimatedDecorationObject(goal.getX(), goal.getY(), width * Size.M, height * Size.M,
				name, frames, animation_time, cycle);
		object.getHandler().getList().add(0, creation);
	}

}
