package com.sunsigne.reversedrebecca.object.interactive;

import com.sunsigne.reversedrebecca.pattern.GenericListener;

public class Action {

	public Action(InteractiveControlObject interactiveControlObject, String name, GenericListener listener, int keyEvent) {
		this.interactiveControlObject = interactiveControlObject;
		this.name = name;
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

	protected GenericListener listener;

	public void doAction() {
		if (listener == null)
			return;

		listener.doAction();
	}

	////////// KEYBOARD ////////////

	private int keyEvent;

	public int getKeyEvent() {
		return keyEvent;
	}

}
