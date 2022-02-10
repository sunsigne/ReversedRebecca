package com.sunsigne.reversedrebecca.object.interactive.puzzler;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.characteristics.tools.KeyToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.interactive.Action;
import com.sunsigne.reversedrebecca.object.interactive.InteractiveControlObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.KeyPuzzle;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class UnlockAction extends Action {

	public UnlockAction(InteractiveControlObject interactiveControlObject) {
		super(interactiveControlObject, null, null, null, 0);

		setName(new Translatable().getTranslatedText("DoorUnlock", file));
		setToolPlayer(new KeyToolPlayer());
		setListener(openPuzzle(interactiveControlObject));
		setKeyEvent(KeyEvent.VK_E);
	}

	////////// INTERACTION ////////////

	private GenericListener openPuzzle(InteractiveControlObject interactiveControlObject) {

		LVL difficulty = interactiveControlObject.getDifficulty();
		GenericListener actionOnWinning = actionOnWinning(interactiveControlObject);

		GenericListener openPuzzle = () -> new KeyPuzzle(difficulty, actionOnWinning).openPuzzle();
		return openPuzzle;
	}

	private GenericListener actionOnWinning(InteractiveControlObject interactiveControlObject) {

		GenericListener actionOnWinning = () -> {
			NullDoorObject nullDoor = new NullDoorObject(interactiveControlObject.getX(),
					interactiveControlObject.getY());
			interactiveControlObject.getHandler().addObject(nullDoor);
			interactiveControlObject.getHandler().removeObject(interactiveControlObject);
		};

		return actionOnWinning;
	}

}
