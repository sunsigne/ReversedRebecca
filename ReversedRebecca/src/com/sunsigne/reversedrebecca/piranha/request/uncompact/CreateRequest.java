package com.sunsigne.reversedrebecca.piranha.request.uncompact;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class CreateRequest implements Request {

	////////// REQUEST ////////////

	public CreateRequest() {
		RequestList.getList().addObject(this);
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
	public void doAction(ExtraBehaviorsObject object, String target) {

		// determinate the position

		String pos = String.valueOf(target.split(":")[1]);
		int x = Integer.parseInt(pos.split("-")[0]);
		int y = Integer.parseInt(pos.split("-")[1]);
		GoalObject goal = new GoalObject(x, y, false);

		// determinate the type of object

		String type = String.valueOf(target.split(":")[0]);

		GameObject creation = determinateCreation(type, goal.getX(), goal.getY());

		// creation of the object

		if (creation != null)
			object.getHandler().getList().add(0, creation);
	}

	private GameObject determinateCreation(String type, int x, int y) {

		int red = Integer.parseInt(type.split("-")[0]);
		int green = Integer.parseInt(type.split("-")[1]);
		int blue = Integer.parseInt(type.split("-")[2]);

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
