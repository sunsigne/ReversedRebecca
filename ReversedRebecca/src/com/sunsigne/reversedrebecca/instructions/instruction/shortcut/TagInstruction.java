package com.sunsigne.reversedrebecca.instructions.instruction.shortcut;

import com.sunsigne.reversedrebecca.instructions.ConditionAnalyser;
import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.instructions.TagList;
import com.sunsigne.reversedrebecca.instructions.instruction.GotoInstruction;
import com.sunsigne.reversedrebecca.instructions.instruction.Instruction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;

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
	public void doAction(ExtraBehaviorsObject object, String target) {
		ConditionAnalyser condition = ConditionAnalyser.create(target);

		if (condition != null)
			checkTag(condition, object, target);
		else
			// register tag
			TagList.getList().addObject(target);
	}

	private void checkTag(ConditionAnalyser condition, ExtraBehaviorsObject object, String target) {
		for (String tempTag : TagList.getList().getList()) {
			if (tempTag.equalsIgnoreCase(condition.getValueToCheck()))
				condition.setMet(true);
		}

		Instruction instruction = InstructionList.getList().getObject(new GotoInstruction());
		instruction.doAction(object, condition.getAction());
	}

}
