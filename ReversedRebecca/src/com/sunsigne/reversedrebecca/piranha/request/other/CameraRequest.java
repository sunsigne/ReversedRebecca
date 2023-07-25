package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraMovingLaw;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Window;
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
	public void doAction(PiranhaObject object, String target) {
		PhysicLaw law = PhysicList.getList().getObject(new CameraMovingLaw());
		CameraMovingLaw camera = (CameraMovingLaw) law;

		boolean player_fluid = target.equalsIgnoreCase("player_fluid");

		if (target.equalsIgnoreCase("player") || player_fluid) {
			setCameraOnPlayer(camera, player_fluid);
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

	private void setCameraOnPlayer(CameraMovingLaw camera, boolean tempDynamic) {
		camera.setFollowingPlayer(true, tempDynamic);

		if (tempDynamic)
			return;

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		float targetX = -player.getX() + (Window.WIDHT - player.getWidth()) / 2;
		float targetY = -player.getY() + (Window.HEIGHT - player.getHeight()) / 2;
		
		CAMERA.setX(camera.getBorderedTarget(targetX, true, true));
		CAMERA.setY(camera.getBorderedTarget(targetY, false, true));
	}

}
