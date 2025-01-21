package com.sunsigne.reversedrebecca.piranha.request.gotoo;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.condition.local.GotoCondition;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class GotoRequest implements Request {

	////////// REQUEST ////////////

	public GotoRequest() {
		new RequestList().addRequest(this, getType());
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

			if (tempTarget.contains(":")) {
				Request request = new RequestList().getRequestFromType(tempTarget.split(":")[0]);

				try {
					if (request.hasCompactWriting() == false)
						continue;

					request.doAction(object, tempTarget.split(":")[1]);
				} catch (Exception e) {
					System.err.println("Problem encounter with following object : " + object.toString());
					System.err.println("can't process following Instruction : " + "UNKOWNW_CONDITION" + "=" + target);
					System.err.println("following target is not recognized as a valid request : " + tempTarget);
					e.printStackTrace();
				}

			}

			if (!tempTarget.equalsIgnoreCase("null"))
				new GotoCondition().registerValue(object, tempTarget);
		}
	}

}
