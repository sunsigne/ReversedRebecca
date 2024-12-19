package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class CutsceneRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public CutsceneRequest() {
		new RequestList().addRequest(this, getType());
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
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		// target is not analysed in this very case, just write "running".
		return Cutscene.isRunning();
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
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
