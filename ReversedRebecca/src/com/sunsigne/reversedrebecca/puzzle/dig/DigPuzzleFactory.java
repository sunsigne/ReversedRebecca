package com.sunsigne.reversedrebecca.puzzle.dig;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.CyanDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.EasierDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.EasiestDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.GreenKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HarderKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HardestKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.OrangeKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.RedKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.YellowKeyPuzzle;
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
			return new GreenKeyPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new YellowKeyPuzzle(criticalChance, actionOnWinning);
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
			return new GreenKeyPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new YellowKeyPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new OrangeKeyPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new RedKeyPuzzle(criticalChance, actionOnWinning);
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
			return new YellowKeyPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new OrangeKeyPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new RedKeyPuzzle(criticalChance, actionOnWinning);
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
		return "door_unlock";
	}

}
