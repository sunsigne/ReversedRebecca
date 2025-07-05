package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action.TEXT_COLOR;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ColorActionMyself implements Request {

	////////// REQUEST ////////////

	public ColorActionMyself() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new ColorActionMyself();

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

	@Override
	public void doAction(PiranhaObject object, String target) {
		String color = target.split(",")[0].toUpperCase();
		String num = target.split(",")[1].toUpperCase();

		TEXT_COLOR textColor = getTextColor(color);
		Action action = object.getTripleAction().getAction(Integer.parseInt(num) - 1);
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
