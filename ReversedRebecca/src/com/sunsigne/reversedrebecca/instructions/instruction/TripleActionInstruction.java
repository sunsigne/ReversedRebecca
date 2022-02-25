package com.sunsigne.reversedrebecca.instructions.instruction;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.instructions.InstructionList;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

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
		tripleActionInstruction(npc, target);
	}

	private void tripleActionInstruction(NPC npc, String target) {
		String noActionText = null;

		TripleAction tripleAction = new TripleAction(noActionText, getTalkAction(npc, target), null, null);
		npc.setTripleAction(tripleAction);
		npc.createTextAction();

	}

	private Action getTalkAction(NPC npc, String target) {

		String name = new Translatable().getTranslatedText("NPCTalk", npc.getFile());
		GenericListener listener = () -> {
			String text = new Translatable().getTranslatedText(target, npc.getInstructionMap());
			System.out.println(text);
		};
		Action action = new Action(npc, name, listener, KeyEvent.VK_E);

		return action;
	}

}
