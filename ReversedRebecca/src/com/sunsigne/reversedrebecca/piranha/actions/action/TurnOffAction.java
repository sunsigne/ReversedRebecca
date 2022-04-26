package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;
import com.sunsigne.reversedrebecca.piranha.condition.global.TalkedCondition;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.other.TripleActionRequest;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class TurnOffAction extends PiranhaObjectAction {

	////////// NPC ACTION ////////////

	public TurnOffAction() {
		ActionList.getList().addObject(this);
	}

	private static PiranhaObjectAction action = new TurnOffAction();

	@Override
	public PiranhaObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "TURN_OFF_WITHOUT_SAVING";
	}

	////////// LISTENER ////////////

	@Override
	public GenericListener getListener(PiranhaObject object, String target) {
		GenericListener listener = () -> {
			
			Request instruction = RequestList.getList().getObject(new TripleActionRequest());
			instruction.doAction(object, null);
			
			new TalkedCondition().registerValue(target);
		};

		return listener;
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return ActionOneKey.getKey();
	}

}
