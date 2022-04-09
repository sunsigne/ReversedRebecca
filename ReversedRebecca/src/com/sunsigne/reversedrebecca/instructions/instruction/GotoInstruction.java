package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionAnalyzer;
import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;

public class GotoInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public GotoInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new GotoInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "GOTO";
	}

	@Override
	public boolean isShortcut() {
		return false;
	}

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		String[] values = target.split(",");
		String statement = "$->";

		for (String tempValue : values) {

			for (Instruction tempInstruction : InstructionList.getList().getList()) {
				if (!tempInstruction.isShortcut())
					continue;

				if (tempValue.contains(tempInstruction.getType() + ":"))
					tempInstruction.doAction(object, tempValue.split(":")[1]);
			}

			if (!tempValue.equalsIgnoreCase("null"))
				new InstructionAnalyzer(object, statement + tempValue);
		}
	}

}
