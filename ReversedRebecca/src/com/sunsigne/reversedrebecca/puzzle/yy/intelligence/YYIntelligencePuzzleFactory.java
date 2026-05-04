package com.sunsigne.reversedrebecca.puzzle.yy.intelligence;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.yy.strenght.difficulty.CyanYYStrenghtPuzzle;
import com.sunsigne.reversedrebecca.puzzle.yy.strenght.difficulty.RedYYStrenghtPuzzle;

public class YYIntelligencePuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning, GenericListener actionOnLosing) {
		switch (difficulty) {
		case CYAN:
			return new CyanYYStrenghtPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new YellowYYIntelligencePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new RedYYStrenghtPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		default:
			autoWin(actionOnWinning);
			return null;
		}
	}

	////////// SOUND ////////////

	@Override
	public String getOpeningSound() {
		return null;
	}

	@Override
	public String getVictorySound() {
		return null;
	}

}
