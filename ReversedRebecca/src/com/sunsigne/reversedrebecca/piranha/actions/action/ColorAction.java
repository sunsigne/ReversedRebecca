package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.object.piranha.ChoiceObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.AffectingRequest;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;

public abstract class ColorAction extends PiranhaObjectAction {

	public abstract TEXT_COLOR getColor();

	////////// LISTENER ////////////

	@Override
	public GenericListener getListener(PiranhaObject object, String target) {
		GenericListener listener = () -> {

			Request request;
			if (object instanceof ChoiceObject)
				request = new AffectingRequest();
			else
				request = new GotoRequest();

			request.doAction(object, target);

			object.setTripleAction(object.getTripleAction().removeAction(getAction()));
			object.createTextAction();
		};

		return listener;
	}

	////////// KEYBOARD ////////////

	@Override
	public Key getRegisteredKey() {
		return new ActionOneKey();
	}

	@Override
	public int getRegisteredKeyEvent() {
		return ActionOneKey.getKey();
	}

}
