package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.ScreenAnimation;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class CreateScreenAnimationRequest implements Request {

	////////// REQUEST ////////////

	public CreateScreenAnimationRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CreateScreenAnimationRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CREATE_SCREEN_ANIMATION";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		String name = String.valueOf(target.split(",")[0]).toLowerCase();
		int time = Integer.valueOf(target.split(",")[1]);
		int iterations = Integer.valueOf(target.split(",")[2]);
		boolean transition = Boolean.parseBoolean(target.split(",")[3]);

		GameObject creation = new ScreenAnimation(name, time, iterations, transition);
		LAYER.PUZZLE.addObject(creation);
	}

}
