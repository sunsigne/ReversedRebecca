package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerClone;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraMovingLaw;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class CameraWithoutTransitionRequest implements Request, CameraDependency {

	////////// REQUEST ////////////

	public CameraWithoutTransitionRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CameraWithoutTransitionRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CAMERA_WITHOUT_TRANSITION";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		PhysicLaw law = PhysicList.getList().getObject(new CameraMovingLaw());
		CameraMovingLaw camera = (CameraMovingLaw) law;

		if (target.equalsIgnoreCase("player")) {
			PlayerClone player = new PlayerFinder().getPlayerClone();
			if (player != null)
				player.setFollowingPlayer(true);
			
			setCameraOnPlayer(camera);
			return;
		}

		camera.setFollowingPlayer(false);

		if (target.equalsIgnoreCase("fix"))
			return;

		// set camera fixed on pos
		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		CAMERA.setX(-goal.getX());
		CAMERA.setY(-goal.getY());
	}

	private void setCameraOnPlayer(CameraMovingLaw camera) {
		camera.setFollowingPlayer(true, false);

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		float targetX = -player.getX() + (Window.WIDHT - player.getWidth()) / 2;
		float targetY = -player.getY() + (Window.HEIGHT - player.getHeight()) / 2;

		CAMERA.setX(camera.getBorderedTarget(targetX, true, true));
		CAMERA.setY(camera.getBorderedTarget(targetY, false, true));
	}

}
