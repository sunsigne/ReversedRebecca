package com.sunsigne.reversedrebecca.piranha.actions;

import java.lang.reflect.InvocationTargetException;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;

public class ActionAnalyzer {

	public Action getAction(ExtraBehaviorsObject object, String actionInstruction) {
		if (actionInstruction == null)
			return null;

		if (actionInstruction.equalsIgnoreCase("null"))
			return null;

		String actionType = actionInstruction.split(":")[0];
		String target = actionInstruction.split(":")[1];

		ExtraBehaviorsObjectAction genericAction = getExtraBehaviorsObjectAction(actionType);
		ExtraBehaviorsObjectAction objectAction = null;

		if (genericAction == null)
			return null;

		try {
			// creation of a new Instance, otherwise it would override the Action for ALL
			// ExtraBehaviorsObject
			objectAction = genericAction.getAction().getClass().getDeclaredConstructor().newInstance();
			objectAction.create(object, target);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return objectAction;
	}

	private ExtraBehaviorsObjectAction getExtraBehaviorsObjectAction(String actionType) {

		for (ExtraBehaviorsObjectAction tempAction : ActionList.getList().getList()) {
			if (actionType.equalsIgnoreCase(tempAction.getName())) {
				return tempAction;
			}
		}
		return null;
	}

}
