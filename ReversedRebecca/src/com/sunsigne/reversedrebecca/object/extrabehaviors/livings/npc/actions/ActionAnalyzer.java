package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.action.NPCAction;

public class ActionAnalyzer {

	public Action getAction(NPC npc, String actionInstruction) {
		if (actionInstruction == null)
			return null;

		String actionType = actionInstruction.split(":")[0];
		String target = actionInstruction.split(":")[1];

		NPCAction npcAction = getNPCAction(actionType);
		if (npcAction != null)
			npcAction.create(npc, target);

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
