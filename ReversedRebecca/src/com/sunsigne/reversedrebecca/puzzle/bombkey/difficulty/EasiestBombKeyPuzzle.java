package com.sunsigne.reversedrebecca.puzzle.bombkey.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.UnlitBombKeyObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class EasiestBombKeyPuzzle extends EasierBombKeyPuzzle {

	public EasiestBombKeyPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public BombKeyObject getBombKey(Puzzle puzzle, boolean critical) {
		return new UnlitBombKeyObject(puzzle, critical, false, 0, 0);
	}

}
