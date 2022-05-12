package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class SoundRequest implements Request {

	////////// REQUEST ////////////

	public SoundRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new SoundRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "SOUND";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		new SoundTask().play(SOUNDTYPE.SOUND, "sound/" + target.toLowerCase());
	}

}
