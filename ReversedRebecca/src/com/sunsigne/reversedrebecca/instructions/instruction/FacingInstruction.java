package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;

public class FacingInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public FacingInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new FacingInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "FACING";
	}

	@Override
	public void doAction(NPC npc, String target) {
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(target)) {
				npc.setMotionless();
				npc.setFacing(tempFacing);
			}
		}
	}

}
