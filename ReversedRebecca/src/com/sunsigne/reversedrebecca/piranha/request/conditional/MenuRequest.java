package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class MenuRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public MenuRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new MenuRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "MENU";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {
		boolean valueToCheck = Boolean.parseBoolean(String.valueOf(target.split("\\?")[0]));
		boolean isMenuOpen = isMenuOpen();
		return valueToCheck == isMenuOpen;
	}

	private boolean isMenuOpen() {
		if (LAYER.LOADING.getHandler().getList().isEmpty() == false)
			return true;

		if (LAYER.MENU.getHandler().getList().isEmpty() == false && isMenuFading() == false)
			return true;

		return false;
	}

	private boolean isMenuFading() {
		PhysicLaw law = PhysicList.getList().getObject(new FadeMenuLaw());
		return ((FadeMenuLaw) law).isFading();
	}

}
