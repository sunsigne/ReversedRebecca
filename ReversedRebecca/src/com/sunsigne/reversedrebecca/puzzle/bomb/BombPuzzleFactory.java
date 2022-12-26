package com.sunsigne.reversedrebecca.puzzle.bomb;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.CyanBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.EasierBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.EasiestBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.GreenBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.HarderBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.HardestBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.OrangeBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.RedBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.YellowBombPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class BombPuzzleFactory implements PuzzleFactory {

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
			return new EasiestBombPuzzle(criticalChance, actionOnWinning);
		case EASIER:
			return new EasierBombPuzzle(criticalChance, actionOnWinning);
		case HARDER:
			return new HarderBombPuzzle(criticalChance, actionOnWinning);
		case HARDEST:
			return new HardestBombPuzzle(criticalChance, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, int criticalChance, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestBombPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new EasierBombPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new CyanBombPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new GreenBombPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new YellowBombPuzzle(criticalChance, actionOnWinning);
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
			return new CyanBombPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new GreenBombPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new YellowBombPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new OrangeBombPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new RedBombPuzzle(criticalChance, actionOnWinning);
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
			return new YellowBombPuzzle(criticalChance, actionOnWinning);
		case GREEN:
			return new OrangeBombPuzzle(criticalChance, actionOnWinning);
		case YELLOW:
			return new RedBombPuzzle(criticalChance, actionOnWinning);
		case ORANGE:
			return new HarderBombPuzzle(criticalChance, actionOnWinning);
		case RED:
			return new HardestBombPuzzle(criticalChance, actionOnWinning);
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
		return "explosion_large";
	}

}
