package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action.TEXT_COLOR;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ColorActionMyselfRequest implements Request {

	////////// REQUEST ////////////

	public ColorActionMyselfRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new ColorActionMyselfRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "COLOR_ACTION_MYSELF";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	protected PiranhaObject getObject(PiranhaObject object) {
		return object;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		String color = target.split(",")[0].toUpperCase();
		String num = target.split(",")[1].toUpperCase();

		TEXT_COLOR textColor = getTextColor(color);

		PiranhaObject obj = getObject(object);
		if (obj == null)
			return;

		Action action = obj.getTripleAction().getAction(Integer.parseInt(num) - 1);
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
