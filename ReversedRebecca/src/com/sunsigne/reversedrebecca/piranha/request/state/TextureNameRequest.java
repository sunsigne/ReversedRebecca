package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class TextureNameRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public TextureNameRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new TextureNameRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "TEXTURE_NAME";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		object.setTextureName(target);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return object.getTextureName();
	}

}
