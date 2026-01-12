package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.other.FrameImpact;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.other.CameraShakeRequest;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class CreateFrameImpactRequest implements Request {

	////////// REQUEST ////////////

	public CreateFrameImpactRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CreateFrameImpactRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CREATE_FRAME_IMPACT";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		int time = Integer.valueOf(target.split(",")[0]);
		String name = String.valueOf(target.split(",")[1]).toLowerCase();
		String shake = String.valueOf(target.split(",")[2]);

		GameObject creation = new FrameImpact(name);
		Request request = new RequestList().getRequestFromType(new CameraShakeRequest().getType());

		GenericListener listener = () -> {
			LAYER.PUZZLE.addObject(creation);
			request.doAction(object, shake);
		};

		new GameTimer(time, listener);
	}
}
