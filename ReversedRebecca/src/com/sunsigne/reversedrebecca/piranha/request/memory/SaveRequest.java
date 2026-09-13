package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.memory.data.SaveEraserList;
import com.sunsigne.reversedrebecca.piranha.request.memory.data.SaveList;
import com.sunsigne.reversedrebecca.ressources.Save;

public class SaveRequest implements Request {

	////////// REQUEST ////////////

	public SaveRequest() {
		new RequestList().addRequest(this, getType());
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

		if (data.toLowerCase().contains("currentlvl") || data.toLowerCase().contains("surname"))
			return;

		switch (saveType) {

		case "REGISTER":
			System.out.println("AVANT REGISTER ADDING [" + data + "] : " + SaveList.getList().getList());
			SaveList.getList().addObject(data);
			System.out.println("REGISTER : " + data);
			System.out.println("APRES REGISTER ADDING [" + data + "] : " + SaveList.getList().getList());
			break;

		case "CANCEL_REGISTER":
			System.out.println("AVANT REGISTER REMOVE [" + data + "] : " + SaveList.getList().getList());
			SaveList.getList().removeObject(data);
			System.out.println("CANCEL_REGISTER : " + data);
			System.out.println("APRES REGISTER REMOVE [" + data + "] : " + SaveList.getList().getList());
			break;

		case "ERASE":
			System.out.println("AVANT ERASE ADDING [" + data + "] : " + SaveEraserList.getList().getList());
			SaveEraserList.getList().addObject(data);
			System.out.println("ERASE : " + data);
			System.out.println("APRES ERASE ADDING [" + data + "] : " + SaveEraserList.getList().getList());			
			break;

		case "CANCEL_ERASE":
			System.out.println("AVANT ERASE REMOVE [" + data + "] : " + SaveEraserList.getList().getList());
			SaveEraserList.getList().removeObject(data);
			System.out.println("CANCEL_ERASE : " + data);
			System.out.println("APRES ERASE REMOVE [" + data + "] : " + SaveEraserList.getList().getList());
			break;

		case "DAVE":
			new Save().registerDave(data);
			System.out.println("DAVE : " + data);
			break;
		}
	}

}
