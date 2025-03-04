package com.sunsigne.reversedrebecca.piranha.condition;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public abstract class LocalInstruction {

	private boolean userData = false;

	protected void analyse(PiranhaObject object, String condition) {
		this.object = object;

		// if NPC has no PiranhaFile
		if (new FileTask().doesExist(userData, object.getPiranhaFile()) == false)
			return;

		request = new FileTask().read(userData, condition.toUpperCase(), object.getPiranhaFile());

		// if Statement has no correlated Action
		if (request.isBlank())
			return;

		processAction(condition);
	}

	private PiranhaObject object;
	private String request;

	private void processAction(String condition) {
		String requestType = request.split("->")[0];
		String target = request.split("->")[1];

		Request request = new RequestList().getRequestFromType(requestType);

		try {
			request.doAction(object, target);
		} catch (Exception e) {
			System.err.println("Problem encounter with following object : " + object.toString());
			System.err.println("can't process following Instruction : " + condition + "=" + request);
			e.printStackTrace();
		}

	}

}
