package com.sunsigne.reversedrebecca.object.puzzler.door;

import com.sunsigne.reversedrebecca.characteristics.tools.KeyToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.key.KeyPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class UnlockAction extends OpenPuzzleAction {

	public UnlockAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "DoorUnlock";
	}

	////////// TOOL ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new KeyToolPlayer();
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning) {
		return new KeyPuzzleFactory().createPuzzle(devDifficulty, difficulty, toolPlayer, actionOnWinning);
	}

	@Override
	public PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y) {
		COLOR color = ((DoorObject) puzzlerObject).color;
		return new NullDoorObject(color, x, y);
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return ActionOneKey.getKey();
	}

}
