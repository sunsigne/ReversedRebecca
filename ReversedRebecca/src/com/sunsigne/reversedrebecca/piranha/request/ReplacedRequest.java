package com.sunsigne.reversedrebecca.piranha.request;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.compact.DeleteRequest;

public class ReplacedRequest implements Request {

	////////// REQUEST ////////////

	public ReplacedRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new ReplacedRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "REPLACE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		Request deleteRequest = RequestList.getList().getObject(new DeleteRequest());
		Request createRequest = RequestList.getList().getObject(new CreateRequest());

		deleteRequest.doAction(object, target.split(":")[1]);
		createRequest.doAction(object, target);
	}

}
