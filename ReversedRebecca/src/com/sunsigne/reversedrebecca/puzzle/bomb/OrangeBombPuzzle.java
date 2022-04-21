package com.sunsigne.reversedrebecca.puzzle.bomb;

import com.sunsigne.reversedrebecca.object.puzzle.bomb.BombObject;
import com.sunsigne.reversedrebecca.object.puzzle.bomb.MovingBombObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class OrangeBombPuzzle extends BombPuzzle {

	public OrangeBombPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public BombObject getBomb(Puzzle puzzle, int x, int y) {
		MovingBombObject bomb = new MovingBombObject(puzzle, x, y);
		bomb.speed = Size.XS / 3;
		bomb.setVelY(new RandomGenerator().getBoolean() ? bomb.speed : -bomb.speed);
		return bomb;
	}

	@Override
	public void createPuzzle() {
		setRandomMaxCountBetween(2, 5);
	}

	@Override
	public String getVictorySound() {
		return "sound/explosion_large";
	}
	
}
