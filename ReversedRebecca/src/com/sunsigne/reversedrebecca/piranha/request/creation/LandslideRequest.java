package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.other.LandslideObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class LandslideRequest implements IndexRequest {

	////////// REQUEST ////////////

	public LandslideRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new LandslideRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "LANDSLIDE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		System.out.println("go");
		
		// determinate the position

		String pos = String.valueOf(target.split(":")[0]);
		boolean onTheSpot = pos.split("-")[0].equalsIgnoreCase("onthespot");
		int x = onTheSpot ? (object.getX() / Size.M) : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? (object.getY() / Size.M) : Integer.parseInt(pos.split("-")[1]);

		// refine data

		GoalObject goal = new GoalObject(x, y, false);
		x = goal.getX();
		y = goal.getY();

		// determinate the type of object

		String type = String.valueOf(target.split(":")[1]);
		GameObject rubble = determinateCreation(type, x, y);

		// creation of the object

		if (rubble == null)
			return;

		LandslideObject landslide = new LandslideObject(x, y, (RubbleObject) rubble);
		object.getHandler().addObject(landslide);
	}

	private GameObject determinateCreation(String type, int x, int y) {

		int red = 2; // code for mappable RubbleObjet
		int green = Integer.parseInt(type.split("-")[0]);
		int blue = Integer.parseInt(type.split("-")[1]);

		for (Mappable tempMappable : new MapCreator().getList().getList()) {

			int tempRed = tempMappable.getRedCode();
			int tempGreen = tempMappable.getGreenCode();
			int tempBlue = tempMappable.getBlueCode();

			if (red == tempRed && green == tempGreen && blue == tempBlue) {
				return tempMappable.createObject(x, y);
			}
		}
		return null;
	}

}
