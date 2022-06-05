package com.sunsigne.reversedrebecca.puzzle.key;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.UpsideDownLockObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class RedKeyPuzzle extends KeyPuzzle {

	public RedKeyPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		PuzzleObject lock = new UpsideDownLockObject(this, getCol(1), getRow(4));
		KeyObject key = new KeyObject(this, getCol(12), getRow(new RandomGenerator().getIntBetween(1, 6)));
		key.multiplySpeedBy(2);

		LAYER.PUZZLE.addObject(lock);
		LAYER.PUZZLE.addObject(key);
		createRandompWalls(25);
	}

}
