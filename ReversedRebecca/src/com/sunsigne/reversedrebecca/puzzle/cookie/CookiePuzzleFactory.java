package com.sunsigne.reversedrebecca.puzzle.cookie;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.CyanCookiePuzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.EasierCookiePuzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.EasiestCookiePuzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.GreenCookiePuzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.HarderCookiePuzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.HardestCookiePuzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.OrangeCookiePuzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.RedCookiePuzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.difficulty.YellowCookiePuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class CookiePuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
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

	private Puzzle createDevPuzzle(DEV_LVL devDifficulty, ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		switch (devDifficulty) {
		case EASIEST:
			return new EasiestCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case EASIER:
			return new EasierCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case HARDER:
			return new HarderCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case HARDEST:
			return new HardestCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new EasierCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new CyanCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new GreenCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new YellowCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createNormalPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new GreenCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new YellowCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new OrangeCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new RedCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createHardPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new YellowCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new OrangeCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new RedCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new HarderCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new HardestCookiePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
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
