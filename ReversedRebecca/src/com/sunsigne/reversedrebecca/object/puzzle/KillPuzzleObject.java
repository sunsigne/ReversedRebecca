package com.sunsigne.reversedrebecca.object.puzzle;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class KillPuzzleObject extends PuzzleObject implements TickFree, RenderFree, CollisionReactor {

	public KillPuzzleObject(Puzzle puzzle, boolean isCritical, int x, int y) {
		super(puzzle, isCritical, x, y);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : KILL";
		return clazz + " : " + getRow(getX()) + "-" + getCol(getY());
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
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
		GenericListener listener = () -> getPuzzle().closePuzzle(false);
		if (isCritical())
			listener = () -> removeObject();

		collidingReaction(detectorObject, false, listener);
	}

}
