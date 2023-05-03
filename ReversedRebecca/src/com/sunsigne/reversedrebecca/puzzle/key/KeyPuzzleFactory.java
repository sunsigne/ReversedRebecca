package com.sunsigne.reversedrebecca.puzzle.key;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.CyanKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.EasierKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.EasiestKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.GreenKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HarderKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HardestKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.OrangeKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.RedKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.YellowKeyPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class KeyPuzzleFactory implements PuzzleFactory {

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
			return new EasiestKeyPuzzle(toolPlayer, actionOnWinning);
		case EASIER:
			return new EasierKeyPuzzle(toolPlayer, actionOnWinning);
		case HARDER:
			return new HarderKeyPuzzle(toolPlayer, actionOnWinning);
		case HARDEST:
			return new HardestKeyPuzzle(toolPlayer, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestKeyPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new EasierKeyPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new CyanKeyPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new GreenKeyPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new YellowKeyPuzzle(toolPlayer, actionOnWinning);
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
			return new CyanKeyPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new GreenKeyPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new YellowKeyPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new OrangeKeyPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new RedKeyPuzzle(toolPlayer, actionOnWinning);
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
			return new YellowKeyPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new OrangeKeyPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new RedKeyPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new HarderKeyPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new HardestKeyPuzzle(toolPlayer, actionOnWinning);
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
