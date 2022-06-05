package com.sunsigne.reversedrebecca.puzzle.bomb;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;

public class BombPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////
	
	@Override
	public Puzzle createPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanBombPuzzle(actionOnWinning);
		case GREEN:
			return new GreenBombPuzzle(actionOnWinning);
		case YELLOW:
			return new YellowBombPuzzle(actionOnWinning);
		case ORANGE:
			return new OrangeBombPuzzle(actionOnWinning);
		case RED:
			return new RedBombPuzzle(actionOnWinning);
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
		return "sound/explosion_large";
	}

}
