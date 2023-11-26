package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class HighlightAbovePlayerRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public HighlightAbovePlayerRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new HighlightAbovePlayerRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "HIGHTLIGHT_ABOVE_PLAYER";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		boolean hightligh_above_player = Boolean.parseBoolean(target);
		object.setHighlightAbovePlayer(hightligh_above_player);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return String.valueOf(object.isHighlightAbovePlayer());
	}

}
