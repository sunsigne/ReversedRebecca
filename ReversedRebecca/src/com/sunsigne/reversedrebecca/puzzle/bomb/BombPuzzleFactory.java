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
			return new EasiestBombPuzzle(actionOnWinning);
		case EASIER:
			return new EasierBombPuzzle(actionOnWinning);
		case HARDER:
			return new HarderBombPuzzle(actionOnWinning);
		case HARDEST:
			return new HardestBombPuzzle(actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestBombPuzzle(actionOnWinning);
		case GREEN:
			return new EasierBombPuzzle(actionOnWinning);
		case YELLOW:
			return new CyanBombPuzzle(actionOnWinning);
		case ORANGE:
			return new GreenBombPuzzle(actionOnWinning);
		case RED:
			return new YellowBombPuzzle(actionOnWinning);
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

	public Puzzle createHardPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new YellowBombPuzzle(actionOnWinning);
		case GREEN:
			return new OrangeBombPuzzle(actionOnWinning);
		case YELLOW:
			return new RedBombPuzzle(actionOnWinning);
		case ORANGE:
			return new HarderBombPuzzle(actionOnWinning);
		case RED:
			return new HardestBombPuzzle(actionOnWinning);
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
