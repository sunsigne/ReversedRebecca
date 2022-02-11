package com.sunsigne.reversedrebecca.object.interactive.puzzler;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.interactive.Action;
import com.sunsigne.reversedrebecca.object.interactive.InteractiveControlObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public abstract class OpenPuzzleAction extends Action {

	public OpenPuzzleAction(InteractiveControlObject interactiveControlObject) {
		super(interactiveControlObject, null, null, null, 0);

		setName(new Translatable().getTranslatedText(getName(), file));
		setToolPlayer(getToolPlayer());
		setListener(() -> {
			Puzzle puzzle = getPuzzle(interactiveControlObject.getDifficulty(), actionOnWinning(interactiveControlObject));
			puzzle.openPuzzle();
		});				
		setKeyEvent(KeyEvent.VK_E);
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// TOOL ////////////

	public abstract ToolPlayer getToolPlayer();

	////////// PUZZLE ////////////

	public abstract Puzzle getPuzzle(LVL difficulty, GenericListener actionOnWinning);

	public abstract PuzzlerObject getNullObject(int x, int y);

	protected GenericListener actionOnWinning(InteractiveControlObject interactiveControlObject) {

		GenericListener actionOnWinning = () -> {
			PuzzlerObject nullObject = getNullObject(interactiveControlObject.getX(), interactiveControlObject.getY());
			if (nullObject != null)
				interactiveControlObject.getHandler().addObject(nullObject);
			interactiveControlObject.getHandler().removeObject(interactiveControlObject);
		};
		return actionOnWinning;
	}

}
