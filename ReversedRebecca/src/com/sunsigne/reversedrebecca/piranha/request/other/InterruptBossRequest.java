package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class InterruptBossRequest implements Request {

	////////// REQUEST ////////////

	public InterruptBossRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new InterruptBossRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "INTERRUPT_BOSS";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		var handler = object.getHandler();

		var list = new ListCloner().deepCloneByClass(handler, BossObject.class);
		for (BossObject tempBoss : list.getList())
			tempBoss.interrupt();
	}

}
