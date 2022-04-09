package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.instruction.shortcut.DeleteInstruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;

public class ReplacedInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public ReplacedInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new ReplacedInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "REPLACE";
	}

	@Override
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		Instruction deleteInstruction = InstructionList.getList().getObject(new DeleteInstruction());
		Instruction createInstruction = InstructionList.getList().getObject(new CreateInstruction());

		deleteInstruction.doAction(object, target.split(":")[1]);
		createInstruction.doAction(object, target);
	}

}
