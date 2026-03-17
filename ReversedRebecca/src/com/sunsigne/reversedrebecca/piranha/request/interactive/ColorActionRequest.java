package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action.TEXT_COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ColorActionRequest implements IndexRequest {

	////////// REQUEST ////////////

	public ColorActionRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new ColorActionRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "COLOR_ACTION";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		String color = target.split(",")[0].toUpperCase();
		String num = target.split(",")[1].toUpperCase();
		String pos = target.split(",")[2].toUpperCase();

		GameObject gameObject = getGameObject(object, pos);
		if (gameObject instanceof Interactive == false)
			return;

		Interactive interactive = (Interactive) gameObject;
		TEXT_COLOR textColor = getTextColor(color);
		Action action = interactive.getTripleAction().getAction(Integer.parseInt(num) - 1);
		
		if (action != null)
			action.setTextColor(textColor);
	}

	private TEXT_COLOR getTextColor(String color) {
		for (TEXT_COLOR tempTextColor : TEXT_COLOR.values()) {
			if (tempTextColor.getName().equalsIgnoreCase(color))
				return tempTextColor;
		}

		return TEXT_COLOR.WHITE;
	}

}
