package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.ActionAnalyzer;

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
	public void doAction(LivingObject living, String target) {
		if (living instanceof NPC == false)
			return;

		NPC npc = (NPC) living;

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
