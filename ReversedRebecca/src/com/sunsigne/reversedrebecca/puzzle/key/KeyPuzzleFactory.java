package com.sunsigne.reversedrebecca.puzzle.key;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class KeyPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////
	
	@Override
	public Puzzle createPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch(DifficultyOption.getDifficulty()) {
		case EASY:
			return createEasyPuzzle(difficulty, actionOnWinning);
		case NORMAL:
			return createNormalPuzzle(difficulty, actionOnWinning);
		case HARD:
			break;
		}
		
		// should not occurs
		return null;
	}
	
	private Puzzle createEasyPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestKeyPuzzle(actionOnWinning);
		case GREEN:
			return new EasierKeyPuzzle(actionOnWinning);
		case YELLOW:
			return new CyanKeyPuzzle(actionOnWinning);
		case ORANGE:
			return new GreenKeyPuzzle(actionOnWinning);
		case RED:
			return new YellowKeyPuzzle(actionOnWinning);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}
	
	public Puzzle createNormalPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanKeyPuzzle(actionOnWinning);
		case GREEN:
			return new GreenKeyPuzzle(actionOnWinning);
		case YELLOW:
			return new YellowKeyPuzzle(actionOnWinning);
		case ORANGE:
			return new OrangeKeyPuzzle(actionOnWinning);
		case RED:
			return new RedKeyPuzzle(actionOnWinning);
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
		return "sound/door_unlock";
	}

}
