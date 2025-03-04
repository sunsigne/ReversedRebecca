package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.other.AnimatedDecorationObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class CreateAnimationRequest implements IndexRequest {

	////////// REQUEST ////////////

	public CreateAnimationRequest() {
		new RequestList().addRequest(this, getType());
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
		boolean onTheSpot = pos.split("-")[0].equalsIgnoreCase("onthespot");
		int x = onTheSpot ? (object.getX() / Size.M) : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? (object.getY() / Size.M) : Integer.parseInt(pos.split("-")[1]);

		// determinate the index

		String data = String.valueOf(target.split(":")[1]);
		String subLayer = String.valueOf(data.split(",")[0]);
		Handler handler = getSubLayer(object, subLayer);
		int index = getIndex(handler, onTheSpot ? pos.split("-")[1] : pos.split("-")[2]);

		// determinate the size and the name

		int width = Integer.parseInt(data.split(",")[1]);
		int height = Integer.parseInt(data.split(",")[2]);
		String name = String.valueOf(data.split(",")[3]);

		// determine the animation characteristics

		int animation_time = Integer.parseInt(data.split(",")[4]);
		boolean cycle = Boolean.parseBoolean(data.split(",")[5]);

		// refine data

		GoalObject goal = new GoalObject(x, y, false);
		x = goal.getX();
		y = goal.getY();
		width = width * Size.M;
		height = height * Size.M;
		name = name.toLowerCase();

		// creation of the object

		GameObject creation = new AnimatedDecorationObject(x, y, width, height, name, animation_time, cycle);
		addObject(handler, index, creation);
		Handler.updateHandlerMap(handler, creation);
	}

}
