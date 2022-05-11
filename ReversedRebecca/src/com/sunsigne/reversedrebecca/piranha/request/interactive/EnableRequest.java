package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.ConditionalRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class EnableRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public EnableRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new EnableRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "ENABLE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	public boolean disable() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (isConditional(target))
			doConditionalAction(object, target);
		else
			doEnable(object, target);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return String.valueOf(object.isDisabled());
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {

		String valueToCheck = String.valueOf(target.split("\\?")[0]);
		GameObject gameOject = getGameObject(object, valueToCheck);
		if (gameOject instanceof Interactive == false)
			return false;

		Interactive interactive = (Interactive) gameOject;
		return interactive.isDisabled() != disable();
	}

	private void doEnable(PiranhaObject object, String target) {

		GameObject gameOject = getGameObject(object, target);
		if (gameOject instanceof Interactive == false)
			return;

		Interactive interactive = (Interactive) gameOject;
		interactive.setDisabled(disable());
	}

	private GameObject getGameObject(PiranhaObject object, String target) {

		int x = Integer.parseInt(target.split("-")[0]);
		int y = Integer.parseInt(target.split("-")[1]);
		GoalObject goal = new GoalObject(x, y, false);

		return Handler.getObjectAtPos(object.getHandler(), goal.getX(), goal.getY(), object.getSize());
	}

}
