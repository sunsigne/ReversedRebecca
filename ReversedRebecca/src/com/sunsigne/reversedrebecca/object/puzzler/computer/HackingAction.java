package com.sunsigne.reversedrebecca.object.puzzler.computer;

import com.sunsigne.reversedrebecca.characteristics.tools.MicrochipToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class HackingAction extends OpenPuzzleAction {

	public HackingAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "ComputerHack";
	}

	////////// TOOL ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new MicrochipToolPlayer();
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, GenericListener actionOnWinning) {
		return new HackPuzzleFactory().createPuzzle(devDifficulty, difficulty, actionOnWinning);
	}

	@Override
	public PuzzlerObject getNullObject(int x, int y) {
		return new NullComputerObject(x, y);
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return ActionOneKey.getKey();
	}

}
