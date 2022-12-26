package com.sunsigne.reversedrebecca.object.puzzle;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class KillPuzzleObject extends PuzzleObject implements TickFree, RenderFree, CollisionReactor {

	public KillPuzzleObject(Puzzle puzzle, int x, int y) {
		super(puzzle, false, x, y);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : KILL";
		return clazz + " : " + getRow(getX()) + "-" + getCol(getY());
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return true;
	}

	@Override
	public boolean isBlockingPath() {
		return true;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> getPuzzle().closePuzzle(false));
	}

}
