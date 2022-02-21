package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class NPCAction {

	public NPCAction(NPC npc, String valueToRead) {
		this.npc = npc;
		if (npc.getName() == "error")
			return;

		action = new FileTask().read(valueToRead.toUpperCase(), npc.getActionMap());
		readAction();
	}

	////////// ACTION ////////////

	private NPC npc;
	private String action;

	private void readAction() {
		if (action.contains("GOTO")) {
			String pos = action.substring(6, action.length());
			gotoAction(pos);
		}

	}

	private void gotoAction(String pos) {
		int x = Integer.parseInt(pos.split(",")[0]);
		int y = Integer.parseInt(pos.split(",")[1]);

		GoalObject goal = new GoalObject(x, y);
		npc.setGoal(goal);
	}

}
