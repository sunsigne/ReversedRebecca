package com.sunsigne.reversedrebecca.object.interactive.puzzler.rubble;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.characteristics.tools.BombToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.interactive.InteractiveControlObject;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.KeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class ExplodeRubbleAction extends OpenPuzzleAction {

	public ExplodeRubbleAction(InteractiveControlObject interactiveControlObject) {
		super(interactiveControlObject);
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
	public Puzzle getPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		return new KeyPuzzle(difficulty, actionOnWinning);
	}

	@Override
	public PuzzlerObject getNullObject(int x, int y) {
		return null;
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return KeyEvent.VK_E;
	}
	
}
