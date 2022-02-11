package com.sunsigne.reversedrebecca.object.interactive.puzzler.door;

import com.sunsigne.reversedrebecca.characteristics.tools.KeyToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.interactive.InteractiveControlObject;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.puzzle.KeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class UnlockAction extends OpenPuzzleAction {

	public UnlockAction(InteractiveControlObject interactiveControlObject) {
		super(interactiveControlObject);
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
	public Puzzle getPuzzle(InteractiveControlObject ICO) {
		return new KeyPuzzle(ICO.getDifficulty(), actionOnWinning(ICO));
	}

	@Override
	public PuzzlerObject getNullObject(int x, int y) {
		return new NullDoorObject(x, y);
	}

}
