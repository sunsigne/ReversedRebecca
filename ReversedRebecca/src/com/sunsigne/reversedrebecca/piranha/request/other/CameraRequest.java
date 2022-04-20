package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.laws.CameraMovingLaw;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class CameraRequest implements Request, CameraDependency {

	////////// REQUEST ////////////

	public CameraRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CameraRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CAMERA";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		PhysicLaw law = PhysicList.getList().getObject(new CameraMovingLaw());
		CameraMovingLaw camera = (CameraMovingLaw) law;

		if (target.equalsIgnoreCase("player")) {
			camera.setFollowingPlayer(true);
			return;
		}

		// set camera fixed on pos
		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		camera.setFollowingPlayer(false);
		CAMERA.setX(goal.getX());
		CAMERA.setY(goal.getY());
	}

}
