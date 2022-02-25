package com.sunsigne.reversedrebecca.instructions;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MassiveInstruction {

	protected MassiveInstruction(String value) {
		loadAllNPC();
		createInstructionAnalyzerForAllNPC(value);
	}

	private void createInstructionAnalyzerForAllNPC(String value) {
		for (NPC tempNPC : npc_list.getList())
			new InstructionAnalyzer(tempNPC, value);
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
