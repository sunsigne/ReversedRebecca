package com.sunsigne.reversedrebecca.object.interactive;

import com.sunsigne.reversedrebecca.characteristics.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.pattern.GenericListener;

public class ToolAction extends Action {

	public ToolAction(InteractiveControlObject interactiveControlObject, String name, ToolPlayer toolPlayer, GenericListener listener,
			int keyEvent) {
		super(interactiveControlObject, name, listener, keyEvent);
		this.toolPlayer = toolPlayer;
	}

	////////// INTERACTION ////////////

	private ToolPlayer toolPlayer;

	@Override
	public void doAction() {
		if (listener == null)
			return;

		if (toolPlayer == null) {
			listener.doAction();
			return;
		}

		Difficulty difficultyObject = (Difficulty) getInteractiveControlObject();
		if (difficultyObject.getDifficulty() == toolPlayer.getDifficulty())
			listener.doAction();
	}

}
