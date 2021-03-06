package com.sunsigne.reversedrebecca.object.puzzler.rubble;

import com.sunsigne.reversedrebecca.characteristics.tools.BombToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.BombPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class ExplodeRubbleAction extends OpenPuzzleAction {

	public ExplodeRubbleAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "RubbleExplode";
	}

	////////// TOOL ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new BombToolPlayer();
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, GenericListener actionOnWinning) {
		return new BombPuzzleFactory().createPuzzle(devDifficulty, difficulty, actionOnWinning);
	}

	@Override
	public PuzzlerObject getNullObject(int x, int y) {
		return null;
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return ActionOneKey.getKey();
	}

}
