package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.world.World;

public class EndLevelRequest implements Request {

	////////// REQUEST ////////////

	public EndLevelRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new EndLevelRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "END_LVL";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		Request request = RequestList.getList().getObject(new GotoRequest());
		request.doAction(object, target);
		new Save().registerSave();
		new Save().eraseSave();
		new World("lvl000");
	}

}
