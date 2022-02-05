package com.sunsigne.reversedrebecca.object.interactive;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.pattern.GenericListener;

public class Action {

	public Action(InteractiveControlObject interactiveControlObject, String name, GenericListener listener,
			int keyEvent) {
		this(interactiveControlObject, name, null, listener, keyEvent);
	}

	public Action(InteractiveControlObject interactiveControlObject, String name, ToolPlayer toolPlayer,
			GenericListener listener, int keyEvent) {
		this.interactiveControlObject = interactiveControlObject;
		this.name = name;
		this.toolPlayer = toolPlayer;
		this.keyEvent = keyEvent;
		this.listener = listener;
	}

	private InteractiveControlObject interactiveControlObject;

	public InteractiveControlObject getInteractiveControlObject() {
		return interactiveControlObject;
	}

	////////// NAME ////////////

	private String name;

	protected void setName(String name) {
		this.name = name;
	}

	////////// INTERACTION ////////////

	private ToolPlayer toolPlayer;

	protected void setToolPlayer(ToolPlayer toolPlayer) {
		this.toolPlayer = toolPlayer;
	}

	private GenericListener listener;

	protected void setListener(GenericListener listener) {
		this.listener = listener;
	}

	public boolean canDoAction() {
		
		// no tool is required
		if (toolPlayer == null)
			return true;

		// the object has no difficulty registered
		if (getInteractiveControlObject() instanceof Difficulty == false) {
			return true;
		}

		// comparaison between the object's level and the tool's level
		Difficulty difficultyObject = (Difficulty) getInteractiveControlObject();
		boolean canUseTool = new DifficultyComparator().canUseTool(difficultyObject.getDifficulty(),
				toolPlayer.getDifficulty());

		return canUseTool;
	}

	public void doAction() {
		if (listener == null)
			return;

		if (canDoAction())
			listener.doAction();
	}
	
	////////// RENDER ////////////

	public String getDisplayedText() {
		return "[" + KeyEvent.getKeyText(getKeyEvent()) + "]" + " " + name.toUpperCase();
	}
	
	////////// KEYBOARD ////////////

	private int keyEvent;

	public int getKeyEvent() {
		return keyEvent;
	}

	protected void setKeyEvent(int keyEvent) {
		this.keyEvent = keyEvent;
	}

}
