package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.behaviors;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.KeyboardBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.RenderBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class InteractWithPlayer implements Interactive, TickBehavior, RenderBehavior, KeyboardBehavior {

	public InteractWithPlayer(NPC npc) {
		this.npc = npc;
		loadTripleAction();
		createTextAction();
	}

	////////// BEHAVIOR ////////////

	private NPC npc;

	@Override
	public NPC getExtraBehaviorsObject() {
		return npc;
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

	////////// POSITION ////////////

	@Override
	public int getX() {
		return getExtraBehaviorsObject().getX();
	}

	@Override
	public int getY() {
		return getExtraBehaviorsObject().getY();
	}

	@Override
	public void setX(int x) {
		getExtraBehaviorsObject().setX(x);
	}

	@Override
	public void setY(int y) {
		getExtraBehaviorsObject().setY(y);
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return getExtraBehaviorsObject().getWidth();
	}

	@Override
	public int getHeight() {
		return getExtraBehaviorsObject().getHeight();
	}

	////////// VELOCICY ////////////

	@Override
	public int getVelX() {
		return getExtraBehaviorsObject().getVelX();
	}

	@Override
	public int getVelY() {
		return getExtraBehaviorsObject().getVelY();
	}

	@Override
	public void setVelX(int velX) {
		getExtraBehaviorsObject().setVelX(velX);
	}

	@Override
	public void setVelY(int velY) {
		getExtraBehaviorsObject().setVelY(velY);
	}

	////////// KEYBOARD ////////////

	@Override
	public KeyboardController getKeyBoardController() {
		return getExtraBehaviorsObject().getKeyBoardController();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Interactive.super.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Interactive.super.keyReleased(e);
	}

	////////// UPDATABLE ////////////

	@Override
	public Handler getHandler() {
		return getExtraBehaviorsObject().getHandler();
	}
	
}
