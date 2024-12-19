package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.other.ExclamationObject;
import com.sunsigne.reversedrebecca.object.other.ExpressionObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ExpressionRequest implements Request {

	////////// REQUEST ////////////

	public ExpressionRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new ExpressionRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "EXPRESSION";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		ExpressionObject expression;

		if (target.equalsIgnoreCase("exclamation"))
			expression = new ExclamationObject(object, target);
		else
			expression = new ExpressionObject(object, target);

		object.getHandler().addObject(expression);
	}

}
