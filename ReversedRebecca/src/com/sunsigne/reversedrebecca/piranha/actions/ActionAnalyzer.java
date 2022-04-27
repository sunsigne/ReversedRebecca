package com.sunsigne.reversedrebecca.piranha.actions;

import java.lang.reflect.InvocationTargetException;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;
import com.sunsigne.reversedrebecca.piranha.request.other.TripleActionRequest;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class ActionAnalyzer {

	public Action getAction(PiranhaObject object, String actionInstruction) {
		if (actionInstruction == null)
			return null;

		if (actionInstruction.equalsIgnoreCase("null"))
			return null;

		String actionType = actionInstruction.split(":")[0];
		String target = actionInstruction.split(":")[1];

		PiranhaObjectAction genericAction = getPiranhaObjectAction(actionType);
		PiranhaObjectAction objectAction = null;

		// if action not found, treat it as a goto
		if (genericAction == null)
			objectAction = createNewAction(object, actionType, target);

		try {
			// creation of a new Instance, otherwise it would override the Action for ALL
			// ExtraBehaviorsObject
			if (objectAction == null)
				objectAction = genericAction.getAction().getClass().getDeclaredConstructor().newInstance();
			objectAction.create(object, target);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return objectAction;
	}

	private PiranhaObjectAction getPiranhaObjectAction(String actionType) {

		for (PiranhaObjectAction tempAction : ActionList.getList().getList()) {
			if (actionType.equalsIgnoreCase(tempAction.getName())) {
				return tempAction;
			}
		}
		return null;
	}

	private PiranhaObjectAction createNewAction(PiranhaObject object, String actionType, String target) {

		PiranhaObjectAction defaultAction = new PiranhaObjectAction() {

			@Override
			public PiranhaObjectAction getAction() {
				return this;
			}

			@Override
			public String getName() {
				return actionType;
			}

			@Override
			public GenericListener getListener(PiranhaObject ignore, String ignore2) {
				GenericListener listener = () -> {
					Request instruction = RequestList.getList().getObject(new TripleActionRequest());
					instruction.doAction(object, null);

					Request request = RequestList.getList().getObject(new GotoRequest());
					request.doAction(object, target);
				};
				return listener;
			}

			@Override
			public int getKeyEvent() {
				return ActionOneKey.getKey();
			}
		};

		return defaultAction;
	}

}
