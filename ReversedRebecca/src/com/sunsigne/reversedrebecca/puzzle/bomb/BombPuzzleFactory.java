package com.sunsigne.reversedrebecca.puzzle.bomb;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
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
			return new EasiestBombPuzzle(toolPlayer, actionOnWinning);
		case EASIER:
			return new EasierBombPuzzle(toolPlayer, actionOnWinning);
		case HARDER:
			return new HarderBombPuzzle(toolPlayer, actionOnWinning);
		case HARDEST:
			return new HardestBombPuzzle(toolPlayer, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestBombPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new EasierBombPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new CyanBombPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new GreenBombPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new YellowBombPuzzle(toolPlayer, actionOnWinning);
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
			return new CyanBombPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new GreenBombPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new YellowBombPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new OrangeBombPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new RedBombPuzzle(toolPlayer, actionOnWinning);
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
			return new YellowBombPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new OrangeBombPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new RedBombPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new HarderBombPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new HardestBombPuzzle(toolPlayer, actionOnWinning);
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
