package com.sunsigne.reversedrebecca.puzzle.bombkey;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.CyanBombKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.EasierBombKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.EasiestBombKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.GreenBombKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.HarderBombKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.HardestBombKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.OrangeBombKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.RedBombKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty.YellowBombKeyPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class BombKeyPuzzleFactory implements PuzzleFactory {

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

	private Puzzle createDevPuzzle(DEV_LVL devDifficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		switch (devDifficulty) {
		case EASIEST:
			return new EasiestBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case EASIER:
			return new EasierBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case HARDER:
			return new HarderBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case HARDEST:
			return new HardestBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new EasierBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new CyanBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new GreenBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new YellowBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createNormalPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new CyanBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new GreenBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new YellowBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new OrangeBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new RedBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case PURPLE:
			autoWin(actionOnWinning);
			return null;
		}

		// should not occurs
		return null;
	}

	public Puzzle createHardPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new YellowBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case GREEN:
			return new OrangeBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new RedBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case ORANGE:
			return new HarderBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new HardestBombKeyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
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
		return "match_stick";
	}

	@Override
	public String getVictorySound() {
		return "explosion_large";
	}

	////////// SHAKE ////////////

	@Override
	public SHAKE getVictoryShake() {
		return SHAKE.MEDIUM;
	}

}
