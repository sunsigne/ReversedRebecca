package com.sunsigne.reversedrebecca.puzzle.key;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.KeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.key.LockObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class GreenKeyPuzzle extends KeyPuzzle {

	public GreenKeyPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		PuzzleObject lock = new LockObject(this, getCol(1), getRow(4));
		PuzzleObject key = new KeyObject(this, getCol(12), getRow(new RandomGenerator().getIntBetween(1, 6)));

		LAYER.PUZZLE.addObject(lock);
		LAYER.PUZZLE.addObject(key);
		createRandompWalls(4);
	}

	@Override
	public String getVictorySound() {
		return "sound/door_unlock";
	}

}
