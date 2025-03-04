package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class CreateRequest implements IndexRequest {

	////////// REQUEST ////////////

	public CreateRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CreateRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CREATE";
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
		int x = onTheSpot ? object.getX() : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? object.getY() : Integer.parseInt(pos.split("-")[1]);

		// determinate the index

		Handler handler = object.getHandler();
		int index = getIndex(handler, onTheSpot ? pos.split("-")[1] : pos.split("-")[2]);

		// refine data

		if (onTheSpot == false) {
			GoalObject goal = new GoalObject(x, y, false);
			x = goal.getX();
			y = goal.getY();
		}

		// determinate the type of object

		String type = String.valueOf(target.split(":")[1]);
		GameObject creation = determinateCreation(type, x, y);

		// creation of the object

		if (creation != null) {
			addObject(handler, index, creation);
			Handler.updateHandlerMap(handler, creation);
		}
			
	}

	private GameObject determinateCreation(String type, int x, int y) {
		int red = Integer.parseInt(type.split("-")[0]);
		int green = Integer.parseInt(type.split("-")[1]);
		int blue = Integer.parseInt(type.split("-")[2]);

		return new MapCreator().determinateCreation(red, green, blue, x, y);
	}

}
