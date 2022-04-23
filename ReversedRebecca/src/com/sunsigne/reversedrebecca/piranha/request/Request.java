package com.sunsigne.reversedrebecca.piranha.request;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;

public interface Request {

	////////// REQUEST ////////////

	Request getRequest();

	String getType();

	boolean hasCompactWriting();

	void doAction(PiranhaObject object, String target);

}
