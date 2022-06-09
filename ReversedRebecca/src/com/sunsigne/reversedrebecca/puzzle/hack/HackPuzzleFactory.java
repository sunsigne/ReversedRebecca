package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.CyanHackPuzzle;

public class HackPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////
	
	@Override
	public Puzzle createPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanHackPuzzle(actionOnWinning);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	////////// SOUND ////////////

	@Override
	public String getVictorySound() {
		return "sound/short_circuit";
	}

}
