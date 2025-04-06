package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.Surname;

public class SurnameRequest implements Request {

	////////// REQUEST ////////////

	public SurnameRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new SurnameRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SURNAME";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		String surnameType = target.split(",")[0].toLowerCase();
		String data = target.split(",")[1].toLowerCase();

		new Surname().registerSurname(surnameType, data);
		System.out.println("SURNAME : " + surnameType + "=" + data);
	}

}
