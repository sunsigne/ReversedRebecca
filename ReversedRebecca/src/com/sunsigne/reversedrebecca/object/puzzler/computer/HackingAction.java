package com.sunsigne.reversedrebecca.object.puzzler.computer;

import com.sunsigne.reversedrebecca.characteristics.tools.MicrochipToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.upgrade.UpgradePlayer;
import com.sunsigne.reversedrebecca.object.animation.SuperAnimationObject;
import com.sunsigne.reversedrebecca.object.animation.ZapAnimationObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;

public class HackingAction extends OpenPuzzleAction {

	public HackingAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "Hack";
	}

	////////// TOOL & UPGRADE ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new MicrochipToolPlayer();
	}

	@Override
	public UpgradePlayer getUpgradePlayer() {
		return null;
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning, GenericListener actionOnLosing) {
		return new HackPuzzleFactory().createPuzzle(devDifficulty, difficulty, toolPlayer, actionOnWinning, actionOnLosing);
	}

	@Override
	public PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y) {
		return new NullComputerObject(x, y);
	}

	@Override
	public SuperAnimationObject getAnimationObject(PuzzlerObject puzzlerObject, int x, int y) {
		return new ZapAnimationObject(x, y);
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
