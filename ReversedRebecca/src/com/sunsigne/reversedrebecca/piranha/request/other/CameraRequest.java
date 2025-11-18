package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerClone;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraMovingLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.CameraZoomLaw;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class CameraRequest implements Request, CameraDependency {

	////////// REQUEST ////////////

	public CameraRequest() {
		new RequestList().addRequest(this, getType());
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
	public void doAction(PiranhaObject object, String target) {
		if (target.equalsIgnoreCase("zoom")) {
			PhysicLaw law = PhysicList.getList().getObject(new CameraZoomLaw());
			((CameraZoomLaw) law).setActive(true);
			return;
		}

		PhysicLaw law = PhysicList.getList().getObject(new CameraMovingLaw());
		CameraMovingLaw camera = (CameraMovingLaw) law;

		if (target.equalsIgnoreCase("fix")) {
			camera.setFollowingPlayer(false);
			return;
		}

		if (target.equalsIgnoreCase("player")) {
			PlayerClone player = new PlayerFinder().getPlayerClone();
			if (player != null)
				player.setFollowingPlayer(true);

			camera.setFollowingPlayer(true, true);
			return;
		}

		// set camera fixed on pos
		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);
		setCameraOnPlayer(camera, goal);

	}

	private void setCameraOnPlayer(CameraMovingLaw camera, GoalObject goal) {
		PlayerClone player = new PlayerFinder().getPlayerClone();

		if (player != null) {
			player.setFollowingPlayer(false);
			player.setX(goal.getX());
			player.setY(goal.getY());
		}

		camera.setFollowingPlayer(true, true);
	}

}
