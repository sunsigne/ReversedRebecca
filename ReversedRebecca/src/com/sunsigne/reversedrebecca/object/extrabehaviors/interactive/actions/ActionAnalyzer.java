package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions;

import java.lang.reflect.InvocationTargetException;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action.ObjectAction;

public class ActionAnalyzer {

	public Action getAction(ExtraBehaviorsObject object, String actionInstruction) {
		if (actionInstruction == null)
			return null;

		if (actionInstruction.equalsIgnoreCase("null"))
			return null;

		String actionType = actionInstruction.split(":")[0];
		String target = actionInstruction.split(":")[1];

		ObjectAction genericAction = getNPCAction(actionType);
		ObjectAction objectAction = null;

		if (genericAction == null)
			return null;

		try {
			// creation of a new Instance, otherwise it would override the Action for ALL
			// NPCs
			objectAction = genericAction.getAction().getClass().getDeclaredConstructor().newInstance();
			objectAction.create(object, target);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return objectAction;
	}

	private ObjectAction getNPCAction(String actionType) {

		for (ObjectAction tempAction : ActionList.getList().getList()) {
			if (actionType.equalsIgnoreCase(tempAction.getName())) {
				return tempAction;
			}
		}
		return null;
	}

}
