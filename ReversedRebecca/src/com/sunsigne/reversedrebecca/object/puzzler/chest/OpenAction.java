package com.sunsigne.reversedrebecca.object.puzzler.chest;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.animation.PuzzlerAnimationObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.chest.ChestPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;

public class OpenAction extends OpenPuzzleAction {

	public OpenAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "Open";
	}

	////////// TOOL ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return null;
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning) {
		ChestObject chest = (ChestObject) getInteractive();
		return new ChestPuzzleFactory().createPuzzle(chest.getLootFile(), actionOnWinning);
	}

	@Override
	public PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y) {
		return new NullChestObject(x, y);
	}

	@Override
	public PuzzlerAnimationObject getAnimationObject(PuzzlerObject puzzlerObject, int x, int y) {
		return null;
	}

	////////// KEYBOARD ////////////

	@Override
	public Key getKey() {
		return new ActionOneKey();
	}

	@Override
	public int getKeyEvent() {
		return ActionOneKey.getKey();
	}

}
