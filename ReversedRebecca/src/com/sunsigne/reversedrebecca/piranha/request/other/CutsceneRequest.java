package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class CutsceneRequest implements Request {

	////////// REQUEST ////////////

	public CutsceneRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CutsceneRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CUTSCENE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		switch (target.toLowerCase()) {
		case "start":
			new Cutscene().start();
			break;
		case "stop":
			new Cutscene().stop(false);
			break;
		case "stop_with_delay":
			new Cutscene().stop(true);
			break;
		}
	}

}
