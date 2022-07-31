package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.Snitch;

public class SaveRequest implements Request {

	////////// REQUEST ////////////

	public SaveRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new SaveRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SAVE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		String saveType = target.split(",")[0].toUpperCase();
		String data = target.split(",")[1].toUpperCase();
		new Snitch().registerEntry("SAVE:" + target);

		switch (saveType) {
		
		case "REGISTER":
			SaveList.getList().addObject(data);
			System.out.println("REGISTER : " + data);
			break;
			
		case "CANCEL_REGISTER":
			SaveList.getList().removeObject(data);
			System.out.println("CANCEL_REGISTER : " + data);
			break;
			
		case "ERASE":
			SaveEraserList.getList().addObject(data);
			System.out.println("ERASE : " + data);
			break;
			
		case "CANCEL_ERASE":
			SaveEraserList.getList().removeObject(data);
			System.out.println("CANCEL_ERASE : " + data);
			break;
		}
	}

}
