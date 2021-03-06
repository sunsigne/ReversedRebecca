package com.sunsigne.reversedrebecca.puzzle.key;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.CyanKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.EasierKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.EasiestKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.GreenKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HarderKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HardestKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.OrangeKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.RedKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.YellowKeyPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class KeyPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, GenericListener actionOnWinning) {
		if (devDifficulty != null)
			return createDevPuzzle(devDifficulty, actionOnWinning);

		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			return createEasyPuzzle(difficulty, actionOnWinning);
		case NORMAL:
			return createNormalPuzzle(difficulty, actionOnWinning);
		case HARD:
			return createHardPuzzle(difficulty, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createDevPuzzle(DEV_LVL devDifficulty, GenericListener actionOnWinning) {
		switch (devDifficulty) {
		case EASIEST:
			return new EasiestKeyPuzzle(actionOnWinning);
		case EASIER:
			return new EasierKeyPuzzle(actionOnWinning);
		case HARDER:
			return new HarderKeyPuzzle(actionOnWinning);
		case HARDEST:
			return new HardestKeyPuzzle(actionOnWinning);
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

	public Puzzle createHardPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new YellowKeyPuzzle(actionOnWinning);
		case GREEN:
			return new OrangeKeyPuzzle(actionOnWinning);
		case YELLOW:
			return new RedKeyPuzzle(actionOnWinning);
		case ORANGE:
			return new HarderKeyPuzzle(actionOnWinning);
		case RED:
			return new HardestKeyPuzzle(actionOnWinning);
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
		return "door_unlock";
	}

}
