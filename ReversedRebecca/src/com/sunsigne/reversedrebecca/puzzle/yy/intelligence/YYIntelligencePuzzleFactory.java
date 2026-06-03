package com.sunsigne.reversedrebecca.puzzle.yy.intelligence;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;

public class YYIntelligencePuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListenerBoolean actionOnWinning, GenericListener actionOnLosing) {

		return new AnyYYIntelligencePuzzle(toolPlayer, actionOnWinning, actionOnLosing);
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
