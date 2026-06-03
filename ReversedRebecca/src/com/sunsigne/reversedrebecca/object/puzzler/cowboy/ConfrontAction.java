package com.sunsigne.reversedrebecca.object.puzzler.cowboy;

import com.sunsigne.reversedrebecca.characteristics.tools.CowboyToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.upgrade.UpgradePlayer;
import com.sunsigne.reversedrebecca.object.animation.SuperAnimationObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cowboy.CowboyPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;

public class ConfrontAction extends OpenPuzzleAction {

	public ConfrontAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "Confront";
	}

	////////// TOOL & UPGRADE ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new CowboyToolPlayer();
	}

	@Override
	public UpgradePlayer getUpgradePlayer() {
		return null;
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
		return new CowboyPuzzleFactory().createPuzzle(devDifficulty, difficulty, toolPlayer, actionOnWinning,
				actionOnLosing);
	}

	@Override
	public PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y) {
		return new NullCowboyObject(x, y);
	}

	@Override
	public SuperAnimationObject getAnimationObject(PuzzlerObject puzzlerObject, int x, int y, boolean isCritical) {
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
