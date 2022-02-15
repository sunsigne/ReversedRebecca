package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.Behavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.behaviors.BlockingPath;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Size;

public class NPC extends LivingObject implements Interactive {

	public int speed = Size.XS / 5;

	public NPC(int x, int y) {
		this("error", x, y);
	}

	public NPC(String name, int x, int y) {
		super(name, x, y);
		addNPCBehaviors();
		loadTripleAction();
		createTextAction();
	}

	////////// BEHAVIOR ////////////

	public Behavior blockingPath;

	private void addNPCBehaviors() {

		blockingPath = new BlockingPath(this);
		addBehavior(blockingPath);
	}

	////////// INTERACTIVE ////////////

	private boolean isDisabled;

	@Override
	public boolean isDisabled() {
		return isDisabled;
	}

	@Override
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	private void loadTripleAction() {
		String noActionText = null;

		tripleAction = new TripleAction(noActionText, getTalkAction(), null, null);
	}

	private Action getTalkAction() {

		String name = new Translatable().getTranslatedText("NPCTalk", getFile());
		GenericListener listener = () -> {
			System.out.println("yo");
		};
		Action action = new Action(this, name, listener, KeyEvent.VK_E);

		return action;
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(KeyEvent e) {
		Interactive.super.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		Interactive.super.keyReleased(e);
	}

}
