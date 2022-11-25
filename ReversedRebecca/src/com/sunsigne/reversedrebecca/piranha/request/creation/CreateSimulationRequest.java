package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.other.SimulationAnimatedObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;

public class CreateSimulationRequest implements IndexRequest {

	////////// REQUEST ////////////

	public CreateSimulationRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CreateSimulationRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CREATE_SIMULATION";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// determinate the position
		String pos = String.valueOf(target.split(":")[0]);
		boolean onTheSpot = pos.split("-")[0].equalsIgnoreCase("onthespot");
		int x = onTheSpot ? (object.getX() / Size.M) : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? (object.getY() / Size.M) : Integer.parseInt(pos.split("-")[1]);

		// determinate the size and the name
		String data = String.valueOf(target.split(":")[1]);
		int width = Integer.parseInt(data.split(",")[0]);
		int height = Integer.parseInt(data.split(",")[1]);
		String path = String.valueOf(data.split(",")[2]);
		String name = String.valueOf(data.split(",")[3]);

		// refine data
		GoalObject goal = new GoalObject(x, y, false);
		x = goal.getX();
		y = goal.getY();
		width = 9 * Size.M;
		height = 11 * Size.M;
		path = path.toLowerCase();
		name = name.toLowerCase();

		// creation of the object
		GameObject creation = new SimulationAnimatedObject(x, y, width, height, path, name);
		object.getHandler().getList().add(0, creation);
		
		new SoundTask().playSound(SOUNDTYPE.SOUND, "simulation_on");
	}

}
