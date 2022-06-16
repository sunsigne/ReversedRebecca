package com.sunsigne.reversedrebecca.object.puzzler.door.dev;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.object.characteristics.DevDifficulty;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.door.UnlockAction;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.key.KeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.EasierKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.EasiestKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HarderKeyPuzzle;
import com.sunsigne.reversedrebecca.puzzle.key.difficulty.HardestKeyPuzzle;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;

public class DevUnlockAction extends UnlockAction {

	public DevUnlockAction(PuzzlerObject puzzlerObject) {
		super(puzzlerObject);
	}

	////////// DEV DIFFICULTY ////////////

	private DevDifficulty getDevObject() {
		if (getInteractive() instanceof DevDifficulty == false) {
			new SoundTask().play(SOUNDTYPE.ERROR, "error");
			JOptionPane.showMessageDialog(null, "Bad codding ! You can't cast a DevAction on a non DevObject !");
			new Conductor().stopApp();
		}

		return (DevDifficulty) getInteractive();
	}

	////////// PUZZLE ////////////

	@Override
	public Puzzle getPuzzle(LVL difficulty, GenericListener actionOnWinning) {
		KeyPuzzle puzzle = null;

		switch (getDevObject().getDevDifficulty()) {
		case EASIEST:
			puzzle = new EasiestKeyPuzzle(actionOnWinning);
			break;
		case EASIER:
			puzzle = new EasierKeyPuzzle(actionOnWinning);
			break;
		case HARDER:
			puzzle = new HarderKeyPuzzle(actionOnWinning);
			break;
		case HARDEST:
			puzzle = new HardestKeyPuzzle(actionOnWinning);
			break;
		}
		
		return puzzle;
	}

}
