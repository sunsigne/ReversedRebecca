package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.Stunned;
import com.sunsigne.reversedrebecca.object.other.ExpressionObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class ExpressionRequest implements Request {

	////////// REQUEST ////////////

	public ExpressionRequest() {
		RequestList.getList().addObject(this);
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
	public void doAction(ExtraBehaviorsObject object, String target) {

		if (object instanceof LivingObject) {
			LivingObject living = (LivingObject) object;
			living.addBehavior(new Stunned(living, 1 * Game.SEC));
		}

		ExpressionObject expression = new ExpressionObject(object, target.toLowerCase());
		object.getHandler().addObject(expression);
	}

}
