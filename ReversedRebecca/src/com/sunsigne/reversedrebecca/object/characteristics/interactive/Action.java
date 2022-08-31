package com.sunsigne.reversedrebecca.object.characteristics.interactive;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.system.Snitch;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.KeyAnalyzer;

public abstract class Action {

	public Action(Interactive interactive, String name, GenericListener listener, int keyEvent) {
		this(interactive, name, null, listener, keyEvent);
	}

	public Action(Interactive interactive, String name, ToolPlayer toolPlayer, GenericListener listener, int keyEvent) {
		this.interactive = interactive;
		this.name = name;
		this.toolPlayer = toolPlayer;
		this.keyEvent = keyEvent;
		this.listener = listener;
	}

	private Interactive interactive;

	public Interactive getInteractive() {
		return interactive;
	}

	////////// NAME ////////////

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	////////// INTERACTION ////////////

	private ToolPlayer toolPlayer;

	protected void setToolPlayer(ToolPlayer toolPlayer) {
		this.toolPlayer = toolPlayer;
	}

	private GenericListener listener;

	public void setListener(GenericListener listener) {
		this.listener = listener;
	}

	public boolean canDoAction() {

		// no tool is required
		if (toolPlayer == null)
			return true;

		// the object has no difficulty registered
		if (getInteractive() instanceof Difficulty == false) {
			return true;
		}

		// comparaison between the object's level and the tool's level
		Difficulty difficultyObject = (Difficulty) getInteractive();
		boolean canUseTool = new DifficultyComparator().canUseTool(difficultyObject.getDifficulty(),
				toolPlayer.getDifficulty());

		return canUseTool;
	}

	public void doAction() {
		if (listener == null)
			return;

		if (canDoAction()) {
			listener.doAction();
			new Snitch().registerEntry("ACTION:" + name);
		}
	}

	public void forceAction() {
		if (listener == null)
			return;

		listener.doAction();
		new Snitch().registerEntry("ACTION:" + name);
	}

	////////// RENDER ////////////

	public String getDisplayedText() {
		return "[" + new KeyAnalyzer(keyEvent).getKeyText() + "]" + " " + name.toUpperCase();
	}

	////////// KEYBOARD ////////////

	private int keyEvent;

	public int getKeyEvent() {
		return keyEvent;
	}

	public void setKeyEvent(int keyEvent) {
		this.keyEvent = keyEvent;
	}

}
