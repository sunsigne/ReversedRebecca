package com.sunsigne.reversedrebecca.instructions.instruction;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.action.NPCAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.action.TalkAction;

public class TalkInstruction implements Instruction {

	////////// INSTRUCTION ////////////

	public TalkInstruction() {
		InstructionList.getList().addObject(this);
	}

	private static Instruction instruction = new TalkInstruction();

	@Override
	public Instruction getInstruction() {
		return instruction;
	}

	@Override
	public String getType() {
		return "TALK";
	}
	
	@Override
	public boolean isShortcut() {
		return false;
	}
	
	@Override
	public void doAction(LivingObject living, String target) {
		if (living instanceof NPC == false)
			return;

		NPC npc = (NPC) living;
		NPCAction action = new TalkAction();
		action.setListener(action.getListener(npc, target));
		action.doAction();
	}

}
