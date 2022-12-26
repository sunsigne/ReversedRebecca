package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.CyanHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.EasierHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.EasiestHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.GreenHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.HarderHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.HardestHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.OrangeHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.RedHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.YellowHackPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class HackPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, int criticalChance, GenericListener actionOnWinning) {
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
			return new EasiestHackPuzzle(criticalChance, actionOnWinning);
		case EASIER:
			return new EasierHackPuzzle(criticalChance, actionOnWinning);
		case HARDER:
			return new HarderHackPuzzle(criticalChance, actionOnWinning);
		case HARDEST:
			return new HardestHackPuzzle(criticalChance, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, int criticalChance, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestHackPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new EasierHackPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new CyanHackPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new GreenHackPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new YellowHackPuzzle(criticalChance, actionOnWinning);
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
			return new CyanHackPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new GreenHackPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new YellowHackPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new OrangeHackPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new RedHackPuzzle(criticalChance, actionOnWinning);
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
			return new YellowHackPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new OrangeHackPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new RedHackPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new HarderHackPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new HardestHackPuzzle(criticalChance, actionOnWinning);
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
		return "short_circuit";
	}

}
