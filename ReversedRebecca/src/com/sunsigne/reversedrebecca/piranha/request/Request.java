package com.sunsigne.reversedrebecca.piranha.request;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;

public interface Request {

	////////// REQUEST ////////////

	Request getRequest();

	String getType();

	boolean hasCompactWriting();

	void doAction(ExtraBehaviorsObject object, String target);

}
