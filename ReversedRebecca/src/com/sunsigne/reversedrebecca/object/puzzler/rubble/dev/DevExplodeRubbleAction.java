package com.sunsigne.reversedrebecca.object.puzzler.rubble.dev;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.object.characteristics.DevDifficulty;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.ExplodeRubbleAction;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.EasierBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.EasiestBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.HarderBombPuzzle;
import com.sunsigne.reversedrebecca.puzzle.bomb.difficulty.HardestBombPuzzle;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;

public class DevExplodeRubbleAction extends ExplodeRubbleAction {

	public DevExplodeRubbleAction(PuzzlerObject puzzlerObject) {
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
		Puzzle puzzle = null;

		switch (getDevObject().getDevDifficulty()) {
		case EASIEST:
			puzzle = new EasiestBombPuzzle(actionOnWinning);
			break;
		case EASIER:
			puzzle = new EasierBombPuzzle(actionOnWinning);
			break;
		case HARDER:
			puzzle = new HarderBombPuzzle(actionOnWinning);
			break;
		case HARDEST:
			puzzle = new HardestBombPuzzle(actionOnWinning);
			break;
		}

		return puzzle;
	}

}
