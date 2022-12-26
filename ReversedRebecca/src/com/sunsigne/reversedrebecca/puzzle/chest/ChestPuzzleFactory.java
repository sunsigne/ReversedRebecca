package com.sunsigne.reversedrebecca.puzzle.chest;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;

public class ChestPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	@Deprecated // use instead : createPuzzle(String file, GenericListener actionOnWinning)
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, int criticalChance, GenericListener actionOnWinning) {
		// will stop the app
		return new ChestPuzzle("wrong \"createPuzzle\" method used from ChestPuzzleFactory", actionOnWinning);
	}

	public Puzzle createPuzzle(String lootFile, GenericListener actionOnWinning) {
		return new ChestPuzzle(lootFile, actionOnWinning);
	}

	////////// SOUND ////////////

	@Override
	public String getVictorySound() {
		return "door_unlock";
	}

}
