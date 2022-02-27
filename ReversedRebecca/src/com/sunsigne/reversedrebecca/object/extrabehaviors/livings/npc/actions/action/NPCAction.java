package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.action;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public abstract class NPCAction extends Action {

	public NPCAction() {
		super(null, null, null, 0);
	}

	public abstract NPCAction getAction();

	public void create(NPC npc, String target) {
		setName(new Translatable().getTranslatedText(getName(), npc.getFile()));
		setListener(getListener(npc, target));
		setKeyEvent(getKeyEvent());
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// LISTENER ////////////

	public abstract GenericListener getListener(NPC npc, String target);

	////////// KEYBOARD ////////////

	public abstract int getKeyEvent();

}
