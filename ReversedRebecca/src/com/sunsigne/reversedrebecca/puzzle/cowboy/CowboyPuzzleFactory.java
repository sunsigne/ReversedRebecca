package com.sunsigne.reversedrebecca.puzzle.cowboy;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.cowboy.difficulty.CyanCowboyPuzzle;

public class CowboyPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning, GenericListener actionOnLosing) {

		switch (difficulty) {
		case PURPLE:
			autoWin(actionOnWinning);
		default:
			return new CyanCowboyPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		}
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
