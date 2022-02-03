package com.sunsigne.reversedrebecca.object.interactive;

import com.sunsigne.reversedrebecca.characteristics.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
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

	public String getName() {
		return name;
	}

	////////// INTERACTION ////////////

	private ToolPlayer toolPlayer;
	private GenericListener listener;

	public void doAction() {
		if (listener == null)
			return;

		if (toolPlayer == null) {
			listener.doAction();
			return;
		}

		if (getInteractiveControlObject() instanceof Difficulty == false) {
			System.err.println(
					"the InteractiveControlObject need to implements Difficulty if a ToolPlayer stands in the constructor of an Action");
			return;
		}

		Difficulty difficultyObject = (Difficulty) getInteractiveControlObject();

		if (difficultyObject.getDifficulty() == toolPlayer.getDifficulty())
			listener.doAction();
	}

	////////// KEYBOARD ////////////

	private int keyEvent;

	public int getKeyEvent() {
		return keyEvent;
	}

}
