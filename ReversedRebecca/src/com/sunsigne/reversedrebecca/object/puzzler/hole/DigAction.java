package com.sunsigne.reversedrebecca.object.puzzler.hole;

import com.sunsigne.reversedrebecca.characteristics.tools.ShovelToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.key.KeyPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class DigAction extends OpenPuzzleAction {

	public DigAction(PuzzlerObject puzzlerObject, DIRECTION facing) {
		super(puzzlerObject);
		this.facing = facing;
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

	private DIRECTION facing;

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, GenericListener actionOnWinning) {
		return new KeyPuzzleFactory().createPuzzle(devDifficulty, difficulty, actionOnWinning);
	}

	@Override
	public PuzzlerObject getNullObject(int x, int y) {
		return new NullHoleObject(facing, x, y);
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return ActionOneKey.getKey();
	}

}
