package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Pusher.PUSHING_DIRECTION;
import com.sunsigne.reversedrebecca.object.other.CollapseObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.MappableRubble;

public class CollapseRequest implements IndexRequest {

	////////// REQUEST ////////////

	public CollapseRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CollapseRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "COLLAPSE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// determinate the position

		String pos = String.valueOf(target.split(",")[1]);
		boolean onTheSpot = pos.split("-")[0].equalsIgnoreCase("onthespot");
		int x = onTheSpot ? (object.getX() / Size.M) : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? (object.getY() / Size.M) : Integer.parseInt(pos.split("-")[1]);

		// refine data

		GoalObject goal = new GoalObject(x, y, false);
		x = goal.getX();
		y = goal.getY();

		// determinate the type of object

		PUSHING_DIRECTION pushingDirection = getPushingDirection(String.valueOf(target.split(",")[0]));
		String type = String.valueOf(target.split(",")[2]);
		GameObject rubble = determinateCreation(type, x, y);

		// creation of the object

		if (rubble == null)
			return;

		CollapseObject collapse = new CollapseObject(x, y, (RubbleObject) rubble, pushingDirection);
		object.getHandler().addObject(collapse);
	}

	private DIRECTION getFacing(String target) {
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(target))
				return tempFacing;
		}
		return null;
	}

	private PUSHING_DIRECTION getPushingDirection(String target) {
		switch (getFacing(target)) {
		case LEFT:
			return PUSHING_DIRECTION.LEFT;
		case RIGHT:
			return PUSHING_DIRECTION.RIGHT;
		case UP:
			return PUSHING_DIRECTION.UP;
		case DOWN:
			return PUSHING_DIRECTION.DOWN;
		default:
			return null;
		}
	}

	private GameObject determinateCreation(String type, int x, int y) {
		int red = MappableRubble.getRed();
		int green = Integer.parseInt(type.split("-")[0]);
		int blue = Integer.parseInt(type.split("-")[1]);

		return new MapCreator().determinateCreation(red, green, blue, x, y);
	}

}
