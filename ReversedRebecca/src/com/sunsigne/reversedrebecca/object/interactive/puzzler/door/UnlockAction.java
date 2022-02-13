package com.sunsigne.reversedrebecca.object.interactive.puzzler.door;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.characteristics.tools.KeyToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.interactive.InteractiveControlObject;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.key.KeyPuzzleFactory;

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
	public Puzzle getPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		return new KeyPuzzleFactory().createPuzzle(difficulty, actionOnWinning);
	}

	@Override
	public PuzzlerObject getNullObject(int x, int y) {
		return new NullDoorObject(x, y);
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return KeyEvent.VK_E;
	}

}
