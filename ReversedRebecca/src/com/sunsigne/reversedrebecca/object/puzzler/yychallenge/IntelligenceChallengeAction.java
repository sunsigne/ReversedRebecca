package com.sunsigne.reversedrebecca.object.puzzler.yychallenge;

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
import com.sunsigne.reversedrebecca.puzzle.yy.intelligence.YYIntelligencePuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;

public class IntelligenceChallengeAction extends OpenPuzzleAction {

	public IntelligenceChallengeAction(PuzzlerObject puzzlerObject) {
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
		return null;
	}

	@Override
	public UpgradePlayer getUpgradePlayer() {
		return null;
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
		return new YYIntelligencePuzzleFactory().createPuzzle(devDifficulty, difficulty, toolPlayer, actionOnWinning,
				actionOnLosing);
	}

	@Override
	public PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y) {
		return null;
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
