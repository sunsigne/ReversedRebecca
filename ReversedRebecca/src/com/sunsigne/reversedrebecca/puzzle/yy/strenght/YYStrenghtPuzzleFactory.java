package com.sunsigne.reversedrebecca.puzzle.yy.strenght;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.puzzle.disco.difficulty.CyanDiscoPuzzle;
import com.sunsigne.reversedrebecca.puzzle.yy.strenght.difficulty.NormalYYStrenghtPuzzle;
import com.sunsigne.reversedrebecca.system.DifficultyOption;

public class YYStrenghtPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning, GenericListener actionOnLosing) {

		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			return new NormalYYStrenghtPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case NORMAL:
			return new NormalYYStrenghtPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
		case HARD:
			return new NormalYYStrenghtPuzzle(toolPlayer, actionOnWinning, actionOnLosing);
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
