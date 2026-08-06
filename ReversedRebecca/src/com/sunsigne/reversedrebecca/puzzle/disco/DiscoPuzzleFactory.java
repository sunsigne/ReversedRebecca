package com.sunsigne.reversedrebecca.puzzle.disco;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.disco.difficulty.CyanDiscoPuzzle;
import com.sunsigne.reversedrebecca.puzzle.disco.difficulty.YellowDiscoPuzzle;

public class DiscoPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {
		switch (difficulty) {
		case CYAN:
			return new CyanDiscoPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case YELLOW:
			return new YellowDiscoPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case RED:
			return new YellowDiscoPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		default:
			autoWin(actionOnWinning);
			return null;
		}
	}

	////////// SOUND ////////////

	@Override
	public String getOpeningSound() {
		return "match_stick";
	}

	@Override
	public String getVictorySound() {
		return "loot_chest";
	}

}
