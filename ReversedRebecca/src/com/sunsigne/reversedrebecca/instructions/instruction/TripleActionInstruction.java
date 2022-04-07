package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.ActionAnalyzer;

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
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		String[] values = new String[] {};
		if (target != null)
			values = target.split(",");

		String actionInstruction1 = values.length > 0 ? values[0] : null;
		String actionInstruction2 = values.length > 1 ? values[1] : null;
		String actionInstruction3 = values.length > 2 ? values[2] : null;

		String noActionText = null;
		Action action1 = new ActionAnalyzer().getAction(object, actionInstruction1);
		Action action2 = new ActionAnalyzer().getAction(object, actionInstruction2);
		Action action3 = new ActionAnalyzer().getAction(object, actionInstruction3);

		TripleAction tripleAction = new TripleAction(noActionText, action1, action2, action3);

		object.setTripleAction(tripleAction);
		object.createTextAction();
	}

}
