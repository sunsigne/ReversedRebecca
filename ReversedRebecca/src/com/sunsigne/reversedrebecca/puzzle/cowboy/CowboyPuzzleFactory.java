package com.sunsigne.reversedrebecca.puzzle.cowboy;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.cowboy.difficulty.CyanCowboyPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class CowboyPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning, GenericListener actionOnLosing) {
		if (devDifficulty != null)
			return createDevPuzzle(devDifficulty, toolPlayer, actionOnWinning, actionOnLosing);

		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			return createEasyPuzzle(difficulty, toolPlayer, actionOnWinning, actionOnLosing);
		case NORMAL:
			return createNormalPuzzle(difficulty, toolPlayer, actionOnWinning, actionOnLosing);
		case HARD:
			return createHardPuzzle(difficulty, toolPlayer, actionOnWinning, actionOnLosing);
		}

		// should not occurs
		return null;
	}

	private Puzzle createDevPuzzle(DEV_LVL devDifficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		switch (devDifficulty) {
		case EASIEST:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case EASIER:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case HARDER:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case HARDEST:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createNormalPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createHardPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning, GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	////////// SOUND ////////////

	@Override
	public String getOpeningSound() {
		return "gun_loading";
	}

	@Override
	public String getVictorySound() {
		return null;
	}

}
