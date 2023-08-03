package com.sunsigne.reversedrebecca.puzzle.disco;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.disco.difficulty.CyanDiscoPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class DiscoPuzzleFactory implements PuzzleFactory {

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
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case EASIER:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case HARDER:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case HARDEST:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
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
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
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
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning);
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
		return "door_key";
	}

	@Override
	public String getVictorySound() {
		return "door_unlock";
	}

}
