package com.sunsigne.reversedrebecca.puzzle.dig;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.CyanDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.EasierDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.EasiestDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.GreenDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.OrangeDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.RedDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.YellowDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HarderKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HardestKeyPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class DigPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, int criticalChance,
			GenericListener actionOnWinning) {
		if (devDifficulty != null)
			return createDevPuzzle(devDifficulty, criticalChance, actionOnWinning);

		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			return createEasyPuzzle(difficulty, criticalChance, actionOnWinning);
		case NORMAL:
			return createNormalPuzzle(difficulty, criticalChance, actionOnWinning);
		case HARD:
			return createHardPuzzle(difficulty, criticalChance, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createDevPuzzle(DEV_LVL devDifficulty, int criticalChance, GenericListener actionOnWinning) {
		switch (devDifficulty) {
		case EASIEST:
			return new EasiestDigPuzzle(criticalChance, actionOnWinning);
		case EASIER:
			return new EasierDigPuzzle(criticalChance, actionOnWinning);
		case HARDER:
			return new HarderKeyPuzzle(criticalChance, actionOnWinning);
		case HARDEST:
			return new HardestKeyPuzzle(criticalChance, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, int criticalChance, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestDigPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new EasierDigPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new CyanDigPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new GreenDigPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new YellowDigPuzzle(criticalChance, actionOnWinning);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createNormalPuzzle(LVL difficulty, int criticalChance, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanDigPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new GreenDigPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new YellowDigPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new OrangeDigPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new RedDigPuzzle(criticalChance, actionOnWinning);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createHardPuzzle(LVL difficulty, int criticalChance, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new YellowDigPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new OrangeDigPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new RedDigPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new HarderKeyPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new HardestKeyPuzzle(criticalChance, actionOnWinning);
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
		return "dig_long";
	}

}
