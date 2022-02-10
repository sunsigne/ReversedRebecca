package com.sunsigne.reversedrebecca.object.puzzle;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class KillPuzzleObject extends PuzzleObject implements CollisionReactor {

	public KillPuzzleObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> getPuzzle().closePuzzle(false));
	}

}
