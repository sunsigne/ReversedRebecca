package com.sunsigne.reversedrebecca.piranha.request;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.SightFinder;
import com.sunsigne.reversedrebecca.piranha.ConditionalAnalyser;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class SeeRequest implements Request {

	////////// REQUEST ////////////

	public SeeRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new SeeRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SEE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		ConditionalAnalyser conditional = ConditionalAnalyser.create(target);

		for (Updatable tempUpdatable : object.getHandler().getList()) {
			if (tempUpdatable instanceof ExtraBehaviorsObject == false)
				continue;

			ExtraBehaviorsObject tempObject = (ExtraBehaviorsObject) tempUpdatable;

			if (conditional.getValueToCheck().equalsIgnoreCase("PLAYER")) {
				conditional.setMet(new SightFinder(object, new PlayerFinder().getPlayer()).isGoalInSight());
				break;
			}

			if (tempObject.getName().equalsIgnoreCase(conditional.getValueToCheck())) {
				conditional.setMet(new SightFinder(object, tempObject).isGoalInSight());
				break;
			}
		}

		Request instruction = RequestList.getList().getObject(new GotoRequest());
		instruction.doAction(object, conditional.getAction());
	}

}
