package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.action;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ActionAnalyzer {

	public Action getAction(NPC npc, String actionInstruction) {
		if (actionInstruction == null)
			return null;
		
		this.npc = npc;

		String actionType = actionInstruction.split(":")[0];
		String target = actionInstruction.split(":")[1];

		if (actionType.equalsIgnoreCase("TALK"))
			return getTalkAction(target);
		
		if (actionType.equalsIgnoreCase("TRIP"))
			return getTripAction(target);

		return null;
	}

	private NPC npc;

	private Action getTalkAction(String target) {

		String name = new Translatable().getTranslatedText("NPCTalk", npc.getFile());
		GenericListener listener = () -> {
			String text = new Translatable().getTranslatedText(target, npc.getInstructionMap());
			System.out.println(text);
		};
		Action action = new Action(npc, name, listener, KeyEvent.VK_E);

		return action;
	}
	
	private Action getTripAction(String target) {

		String name = new Translatable().getTranslatedText("NPCTrip", npc.getFile());
		GenericListener listener = () -> {
			System.out.println("i'm falling!");
		};
		Action action = new Action(npc, name, listener, KeyEvent.VK_T);

		return action;
	}
	
}
