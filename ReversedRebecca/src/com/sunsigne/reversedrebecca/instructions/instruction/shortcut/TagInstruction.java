package com.sunsigne.reversedrebecca.instructions.instruction.shortcut;

import com.sunsigne.reversedrebecca.instructions.ConditionAnalyser;
import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.TagList;
import com.sunsigne.reversedrebecca.instructions.instruction.GotoInstruction;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;

public class TagInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public TagInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new TagInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "TAG";
	}

	@Override
	public boolean isShortcut() {
		return true;
	}

	@Override
	public void doAction(LivingObject living, String target) {
		ConditionAnalyser condition = ConditionAnalyser.create(target);

		if (condition != null)
			checkTag(condition, living, target);
		else
			// register tag
			TagList.getList().addObject(target);
	}

	private void checkTag(ConditionAnalyser condition, LivingObject living, String target) {
		for (String tempTag : TagList.getList().getList()) {
			if (tempTag.equalsIgnoreCase(condition.getValueToCheck()))
				condition.setMet(true);
		}

		Instruction instruction = InstructionList.getList().getObject(new GotoInstruction());
		instruction.doAction(living, condition.getAction());
	}

}
