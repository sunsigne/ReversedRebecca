package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.physic.finder.NearestOfFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class TeleportRequest implements Request {

	////////// REQUEST ////////////

	public TeleportRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new TeleportRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "TP";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		int x;
		int y;

		// ex : MOVE:10-20
		if (target.contains("-")) {
			x = Integer.parseInt(target.split("-")[0]);
			y = Integer.parseInt(target.split("-")[1]);
		}

		// ex : MOVE:PLAYER
		else {
			var target_object = getObject(object, target);

			if (target_object == null) {
				x = object.getX();
				y = object.getY();
			}

			Position pos = new NearestOfFinder().getPos(object, target_object);

			x = new GoalObject(pos.getX(), 0, true).getX();
			y = new GoalObject(0, pos.getY(), true).getY();
		}

		GoalObject goal = new GoalObject(x, y, false);
		object.setX(goal.getX());
		object.setY(goal.getY());
	}

	private PiranhaObject getObject(PiranhaObject object, String target) {
		var handler = object.getHandler();
		
		for (Updatable tempUpdatable : handler.getList()) {
			if (tempUpdatable instanceof PiranhaObject == false)
				continue;

			PiranhaObject tempObject = (PiranhaObject) tempUpdatable;
			String formated_valueToCheck = new FormattedString().getName(object, target);

			if (tempObject.getName().equalsIgnoreCase(formated_valueToCheck))
				return tempObject;
		}

		return null;
	}
	
}