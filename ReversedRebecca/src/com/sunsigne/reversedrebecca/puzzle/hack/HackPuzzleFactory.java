package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.CyanHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.EasierHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.EasiestHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.GreenHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.YellowHackPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class HackPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(LVL difficulty, GenericListener actionOnWinning) {
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

	private Puzzle createEasyPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestHackPuzzle(actionOnWinning);
		case GREEN:
			return new EasierHackPuzzle(actionOnWinning);
		case YELLOW:
			return new CyanHackPuzzle(actionOnWinning);
		case ORANGE:
			return new GreenHackPuzzle(actionOnWinning);
		case RED:
			return new YellowHackPuzzle(actionOnWinning);
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
			return new CyanHackPuzzle(actionOnWinning);
		case GREEN:
			return new GreenHackPuzzle(actionOnWinning);
		case YELLOW:
			return new YellowHackPuzzle(actionOnWinning);
		case ORANGE:
//			return new OrangeHackPuzzle(actionOnWinning);
		case RED:
//			return new RedHackPuzzle(actionOnWinning);
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
			return new YellowHackPuzzle(actionOnWinning);
		case GREEN:
//			return new OrangeHackPuzzle(actionOnWinning);
		case YELLOW:
//			return new RedHackPuzzle(actionOnWinning);
		case ORANGE:
//			return new HarderHackPuzzle(actionOnWinning);
		case RED:
//			return new HardestHackPuzzle(actionOnWinning);
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
