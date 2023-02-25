package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.other.DecorationObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class CreateDecorationRequest implements IndexRequest {

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

		// refine data

		GoalObject goal = new GoalObject(x, y, false);
		x = goal.getX();
		y = goal.getY();
		width = width * Size.M;
		height = height * Size.M;
		name = name.toLowerCase();

		// creation of the object

		GameObject creation = new DecorationObject(x, y, width, height, name);
		handler.getList().add(index, creation);
	}

}
