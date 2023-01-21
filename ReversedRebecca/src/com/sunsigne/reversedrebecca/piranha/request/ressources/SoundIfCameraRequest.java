package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;

public class SoundIfCameraRequest extends SoundRequest {

	////////// REQUEST ////////////

	public SoundIfCameraRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new SoundIfCameraRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "SOUND_IF_CAMERA";
	}

	@Override
	protected void playSound(PiranhaObject object, String path) {
		new SoundTask().playSoundIfCamera(object, path);
	}

}
