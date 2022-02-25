package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.action.ActionAnalyzer;

public class TripleActionInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public TripleActionInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new TripleActionInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "TRIPLE_ACTION";
	}

	@Override
	public void doAction(NPC npc, String target) {
		String[] values = target.split(",");

		String actionInstruction1 = values.length > 0 ? values[0] : null;
		String actionInstruction2 = values.length > 1 ? values[1] : null;
		String actionInstruction3 = values.length > 2 ? values[2] : null;

		String noActionText = null;
		Action action1 = new ActionAnalyzer().getAction(npc, actionInstruction1);
		Action action2 = new ActionAnalyzer().getAction(npc, actionInstruction2);
		Action action3 = new ActionAnalyzer().getAction(npc, actionInstruction3);

		TripleAction tripleAction = new TripleAction(noActionText, action1, action2, action3);

		npc.setTripleAction(tripleAction);
		npc.createTextAction();
	}

}
