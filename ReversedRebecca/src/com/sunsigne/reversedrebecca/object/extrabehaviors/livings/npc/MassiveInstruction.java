package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MassiveInstruction {

	public MassiveInstruction(String valueToRead) {
		loadAllNPC();
		addActionToAllNPC(valueToRead);
	}

	private void addActionToAllNPC(String valueToRead) {
		for (NPC tempNPC : npc_list.getList())
			new Instruction(tempNPC, valueToRead);
	}

	////////// MAP OR LIST ////////////

	private GameList<NPC> npc_list = new GameList<NPC>(LISTTYPE.LINKED);

	private void loadAllNPC() {
		for (LAYER tempLayer : LAYER.values()) {
			var list = tempLayer.getHandler().getList();

			for (Updatable tempUpdatable : list) {
				if (tempUpdatable instanceof NPC)
					npc_list.addObject((NPC) tempUpdatable);
			}
		}
	}

}
