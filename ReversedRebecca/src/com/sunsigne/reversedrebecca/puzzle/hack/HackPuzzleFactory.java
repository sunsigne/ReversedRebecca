package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.CyanHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.EasierHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.EasiestHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.GreenHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.HarderHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.HardestHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.OrangeHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.RedHackPuzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.difficulty.YellowHackPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class HackPuzzleFactory implements PuzzleFactory {

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
			return new EasiestHackPuzzle(toolPlayer, actionOnWinning);
		case EASIER:
			return new EasierHackPuzzle(toolPlayer, actionOnWinning);
		case HARDER:
			return new HarderHackPuzzle(toolPlayer, actionOnWinning);
		case HARDEST:
			return new HardestHackPuzzle(toolPlayer, actionOnWinning);
		}

		// should not occurs
		return null;
	}

	private Puzzle createEasyPuzzle(LVL difficulty, ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		switch (difficulty) {
		case NULL:
		case CYAN:
			return new EasiestHackPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new EasierHackPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new CyanHackPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new GreenHackPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new YellowHackPuzzle(toolPlayer, actionOnWinning);
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
			return new CyanHackPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new GreenHackPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new YellowHackPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new OrangeHackPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new RedHackPuzzle(toolPlayer, actionOnWinning);
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
			return new YellowHackPuzzle(toolPlayer, actionOnWinning);
		case GREEN:
			return new OrangeHackPuzzle(toolPlayer, actionOnWinning);
		case YELLOW:
			return new RedHackPuzzle(toolPlayer, actionOnWinning);
		case ORANGE:
			return new HarderHackPuzzle(toolPlayer, actionOnWinning);
		case RED:
			return new HardestHackPuzzle(toolPlayer, actionOnWinning);
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
		return "virus";
	}
	
	@Override
	public String getVictorySound() {
		return "short_circuit";
	}
	
	////////// SHAKE ////////////
	
	@Override
	public SHAKE getVictoryShake() {
		return SHAKE.LITTLE;
	}
		
}
