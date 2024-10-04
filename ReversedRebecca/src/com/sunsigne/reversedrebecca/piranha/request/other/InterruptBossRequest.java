package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class InterruptBossRequest implements Request {

	////////// REQUEST ////////////

	public InterruptBossRequest() {
		RequestList.getList().addObject(this);
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
		
		var list = new ListCloner().deepClone(handler);
		for (Updatable tempUpdatable : list.getList()) {
			if (tempUpdatable instanceof BossObject == false)
				continue;

			BossObject tempBoss = (BossObject) tempUpdatable;
			tempBoss.interrupt();
		}
	}

}
