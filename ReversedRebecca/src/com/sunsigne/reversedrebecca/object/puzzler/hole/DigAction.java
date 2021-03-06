package com.sunsigne.reversedrebecca.object.puzzler.hole;

import com.sunsigne.reversedrebecca.characteristics.tools.ShovelToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public abstract class DigAction extends OpenPuzzleAction {

	public DigAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "HoleDig";
	}

	////////// TOOL ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new ShovelToolPlayer();
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, GenericListener actionOnWinning) {
		return new DigPuzzleFactory().createPuzzle(devDifficulty, difficulty, actionOnWinning);
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return ActionOneKey.getKey();
	}

}
