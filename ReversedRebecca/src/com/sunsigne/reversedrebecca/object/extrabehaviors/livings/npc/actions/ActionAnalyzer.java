package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions;

import java.lang.reflect.InvocationTargetException;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.action.NPCAction;

public class ActionAnalyzer {

	public Action getAction(NPC npc, String actionInstruction) {
		if (actionInstruction == null)
			return null;

		if (actionInstruction.equalsIgnoreCase("null"))
			return null;
		
		String actionType = actionInstruction.split(":")[0];
		String target = actionInstruction.split(":")[1];

		NPCAction genericAction = getNPCAction(actionType);
		NPCAction npcAction = null;

		if (genericAction == null)
			return null;

		try {
			// creation of a new Instance, otherwise it would override the Action for ALL NPCs
			npcAction = genericAction.getAction().getClass().getDeclaredConstructor().newInstance();
			npcAction.create(npc, target);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return npcAction;
	}

	private NPCAction getNPCAction(String actionType) {

		for (NPCAction tempAction : ActionList.getList().getList()) {
			if (actionType.equalsIgnoreCase(tempAction.getName())) {
				return tempAction;
			}
		}
		return null;
	}

}
