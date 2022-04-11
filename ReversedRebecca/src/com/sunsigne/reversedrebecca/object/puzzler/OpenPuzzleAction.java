package com.sunsigne.reversedrebecca.object.puzzler;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.piranha.condition.global.WonPuzzleCondition;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public abstract class OpenPuzzleAction extends Action {

	public OpenPuzzleAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject, null, null, null, 0);

		setName(new Translatable().getTranslatedText(getName(), puzzlerObject.getFile()));
		setToolPlayer(getToolPlayer());
		setListener(() -> {
			Puzzle puzzle = getPuzzle(puzzlerObject.getDifficulty(), actionOnWinning(puzzlerObject));
			puzzle.openPuzzle();
		});
		setKeyEvent(getKeyEvent());
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// TOOL ////////////

	public abstract ToolPlayer getToolPlayer();

	////////// PUZZLE ////////////

	public abstract Puzzle getPuzzle(LVL difficulty, GenericListener actionOnWinning);

	public abstract PuzzlerObject getNullObject(int x, int y);

	protected GenericListener actionOnWinning(PuzzlerObject puzzlerObject) {

		GenericListener actionOnWinning = () -> {
			PuzzlerObject nullObject = getNullObject(puzzlerObject.getX(), puzzlerObject.getY());
			if (nullObject != null)
				puzzlerObject.getHandler().addObject(nullObject);
			puzzlerObject.getHandler().removeObject(puzzlerObject);

			new WonPuzzleCondition().registerValue(puzzlerObject);
		};
		return actionOnWinning;
	}

	////////// KEYBOARD ////////////

	public abstract int getKeyEvent();

}
