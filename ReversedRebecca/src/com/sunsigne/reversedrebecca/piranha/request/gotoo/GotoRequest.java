package com.sunsigne.reversedrebecca.piranha.request.gotoo;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.condition.local.GotoCondition;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class GotoRequest implements Request {

	////////// REQUEST ////////////

	public GotoRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new GotoRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "GOTO";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		String[] targets = target.split(",");

		for (String tempTarget : targets) {

			for (Request tempAction : RequestList.getList().getList()) {
				if (!tempAction.hasCompactWriting())
					continue;

				if (tempTarget.contains(":"))
					if (tempTarget.split(":")[0].equalsIgnoreCase(tempAction.getType()))
						tempAction.doAction(object, tempTarget.split(":")[1]);
			}

			if (!tempTarget.equalsIgnoreCase("null"))
				new GotoCondition().registerValue(object, tempTarget);
		}
	}

}
