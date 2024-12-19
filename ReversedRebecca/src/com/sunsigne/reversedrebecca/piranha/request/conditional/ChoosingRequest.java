package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.ChoiceObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ChoosingRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public ChoosingRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new ChoosingRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CHOOSING";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		boolean valueToCheck = Boolean.parseBoolean(String.valueOf(target.split("\\?")[0]));
		boolean isPlayerChoosing = isPlayerChoosing(object);
		return valueToCheck == isPlayerChoosing;
	}

	protected boolean isPlayerChoosing(PiranhaObject object) {
		Handler handler = object.getHandler();
		
		var list = new ListCloner().deepClone(handler);
		for (Updatable tempUpdatable : list.getList()) {
			if (tempUpdatable instanceof ChoiceObject)
				return true;
		}
		return false;
	}
}
