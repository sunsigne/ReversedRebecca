package com.sunsigne.reversedrebecca.piranha.request.compact;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action.ObjectAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action.TalkAction;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.Request;

public class TalkRequest implements Request {

	////////// REQUEST ////////////

	public TalkRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new TalkRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "TALK";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		ObjectAction action = new TalkAction();
		action.setListener(action.getListener(object, target));
		action.doAction();
	}

}
