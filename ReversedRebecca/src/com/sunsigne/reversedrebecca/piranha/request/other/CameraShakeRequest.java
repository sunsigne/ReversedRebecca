package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class CameraShakeRequest implements Request {

	////////// REQUEST ////////////

	public CameraShakeRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CameraShakeRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CAMERA_SHAKE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		for (SHAKE tempShake : SHAKE.values()) {
			if (tempShake.getName().equalsIgnoreCase(target) == false)
				continue;

			new CameraShaker().shaking(tempShake);
			return;
		}

		int force = Integer.parseInt(target);
		new CameraShaker().shaking(force);
	}

}
