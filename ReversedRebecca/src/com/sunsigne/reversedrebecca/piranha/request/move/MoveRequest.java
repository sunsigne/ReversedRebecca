package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.physic.finder.NearestOfFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MoveRequest implements Request {

	////////// REQUEST ////////////

	public MoveRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new MoveRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "MOVE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.NORMAL;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (target.equalsIgnoreCase("stop")) {
			object.setGoal(null);
			object.setPath(null);
			object.setMotionless();
			return;
		}

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

		GoalObject goal = new GoalObject(x, y, false) {

			@Override
			public boolean doesTriggerGoalCondition() {
				return target.contains("-");
			}
		};

		if (object instanceof SpeedVariator) {
			SpeedVariator variator = (SpeedVariator) object;
			variator.setSpeedness(getSpeedness());
		}

		object.setGoal(goal);
	}

	private PiranhaObject getObject(PiranhaObject object, String target) {
		var handler = object.getHandler();
		
		var list = new ListCloner().deepClone(handler);
		for (Updatable tempUpdatable : list.getList()) {
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
