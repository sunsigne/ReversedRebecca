package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
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
		Player player = new PlayerFinder().getPlayer();

		// set camera following player
		if (target.equalsIgnoreCase("player") && player != null) {
			player.addBehavior(player.cameraFollowing);
			return;
		}

		// set camera fixed on pos
		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		if (player != null)
			player.removeBehavior(player.cameraFollowing);

		CAMERA.setX(goal.getX());
		CAMERA.setY(goal.getY());

	}

	////////// CAMERA ////////////

	@Override
	public boolean isCameraDependant() {
		return false;
	}

}
