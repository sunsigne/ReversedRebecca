package com.sunsigne.reversedrebecca.puzzle.chest;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class ChestPuzzleFactory implements PuzzleFactory {

	////////// PUZZLE ////////////

	@Override
	@Deprecated // use instead : createPuzzle(String lootFile, GenericListener actionOnWinning)
	public Puzzle createPuzzle(DEV_LVL devDifficulty, LVL difficulty, ToolPlayer toolPlayer,
			GenericListener actionOnWinning) {
		// will stop the app
		return new ChestPuzzle("wrong \"createPuzzle\" method used from ChestPuzzleFactory", actionOnWinning);
	}

	public Puzzle createPuzzle(String lootFile, GenericListener actionOnWinning) {
		return new ChestPuzzle(lootFile, actionOnWinning);
	}

	////////// SOUND ////////////
	
	@Override
	public String getOpeningSound() {
		return "chest";
	}
	
	@Override
	public String getVictorySound() {
		GenericListener playSound = () -> new SoundTask().playSound(SOUNDTYPE.SOUND, "loot");
		new GameTimer(5, true, playSound);
		return "chest_open";
	}

}
