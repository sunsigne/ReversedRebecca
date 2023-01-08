package com.sunsigne.reversedrebecca.puzzle.dig;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.CyanDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.EasierDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.EasiestDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.GreenDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.HarderDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.HardestDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.OrangeDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.RedDigPuzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.difficulty.YellowDigPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class DigPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning) {
		if (devDifficulty != null)
			return createDevPuzzle(devDifficulty, toolPlayer, actionOnWinning);

		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			return createEasyPuzzle(difficulty, toolPlayer, actionOnWinning);
		case NORMAL:
			return createNormalPuzzle(difficulty, toolPlayer, actionOnWinning);
		case HARD:
			return createHardPuzzle(difficulty, toolPlayer, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createDevPuzzle(DEV_LVL devDifficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		switch (devDifficulty) {
		case EASIEST:
			return new EasiestDigPuzzle(toolPlayer, actionOnWinning);
		case EASIER:
			return new EasierDigPuzzle(toolPlayer, actionOnWinning);
		case HARDER:
			return new HarderDigPuzzle(toolPlayer, actionOnWinning);
		case HARDEST:
			return new HardestDigPuzzle(toolPlayer, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestDigPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new EasierDigPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new CyanDigPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new GreenDigPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new YellowDigPuzzle(toolPlayer, actionOnWinning);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createNormalPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanDigPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new GreenDigPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new YellowDigPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new OrangeDigPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new RedDigPuzzle(toolPlayer, actionOnWinning);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createHardPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new YellowDigPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new OrangeDigPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new RedDigPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new HarderDigPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new HardestDigPuzzle(toolPlayer, actionOnWinning);
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
