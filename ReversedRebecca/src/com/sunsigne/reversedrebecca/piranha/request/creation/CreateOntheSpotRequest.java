package com.sunsigne.reversedrebecca.piranha.request.creation;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class CreateOntheSpotRequest extends CreateRequest {

	////////// REQUEST ////////////

	public CreateOntheSpotRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CreateOntheSpotRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CREATE_ONTHESPOT";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {
		super.doAction(object, target + ":onthespot");
	}

}
