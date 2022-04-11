package com.sunsigne.reversedrebecca.piranha.condition;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public abstract class LocalInstruction {

	protected void analyse(ExtraBehaviorsObject object, String condition) {
		this.object = object;

		// if NPC has no PiranhaFile
		if (new FileTask().doesExist(object.getPiranhaFile()) == false)
			return;

		request = new FileTask().read(condition.toUpperCase(), object.getPiranhaFile());

		// if Statement has no correlated Action
		if (request.isBlank())
			return;

		processAction();
	}

	private ExtraBehaviorsObject object;
	private String request;

	private void processAction() {
		String requestType = request.split("->")[0];
		String target = request.split("->")[1];

		for (Request tempRequest : RequestList.getList().getList()) {
			if (requestType.equalsIgnoreCase(tempRequest.getType())) {
				tempRequest.doAction(object, target);
			}
		}
	}

}
