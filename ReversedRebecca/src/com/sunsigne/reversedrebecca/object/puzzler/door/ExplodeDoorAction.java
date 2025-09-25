package com.sunsigne.reversedrebecca.object.puzzler.door;

import com.sunsigne.reversedrebecca.characteristics.tools.BombToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.upgrade.BombingDoorUpgrade;
import com.sunsigne.reversedrebecca.characteristics.upgrade.UpgradePlayer;
import com.sunsigne.reversedrebecca.object.animation.ExplosionAnimationObject;
import com.sunsigne.reversedrebecca.object.animation.SuperAnimationObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.BombPuzzleFactory;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;

public class ExplodeDoorAction extends OpenPuzzleAction {

	public ExplodeDoorAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "DoorExplode";
	}

	////////// TOOL & UPGRADE ////////////

	@Override
	public ToolPlayer getToolPlayer() {
		return new BombToolPlayer();
	}

	@Override
	public UpgradePlayer getUpgradePlayer() {
		return new BombingDoorUpgrade();
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning, GenericListener actionOnLosing) {
		return new BombPuzzleFactory().createPuzzle(devDifficulty, difficulty, toolPlayer, actionOnWinning,
				actionOnLosing);
	}

	@Override
	public PuzzlerObject getNullObject(PuzzlerObject puzzlerObject, int x, int y) {
		return null;
	}

	@Override
	public SuperAnimationObject getAnimationObject(PuzzlerObject puzzlerObject, int x, int y) {
		return new ExplosionAnimationObject(x, y);
	}

	////////// KEYBOARD ////////////

	@Override
	public Key getKey() {
		return new ActionTwoKey();
	}

	@Override
	public int getKeyEvent() {
		return ActionTwoKey.getKey();
	}

}
