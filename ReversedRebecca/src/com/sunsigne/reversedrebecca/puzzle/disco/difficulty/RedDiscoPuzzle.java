package com.sunsigne.reversedrebecca.puzzle.disco.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoDancerObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;
import com.sunsigne.reversedrebecca.puzzle.disco.DiscoPuzzle;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class RedDiscoPuzzle extends DiscoPuzzle {

	public RedDiscoPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public int getTimer() {
		return 120 * Game.SEC;
	}

	private boolean secondDancer;

	@Override
	public DiscoDancerObject getDiscoDancer() {
		if (secondDancer)
			return new DiscoDancerObject(this, "stephan", true);

		secondDancer = true;
		return new DiscoDancerObject(this, "rebecca", false);
	}

	@Override
	public void createPuzzle() {
		int time = 6 * Game.SEC;

		createPlayerArrows(DIRECTION.UP);

		switchSide(false, 2*time);
		switchSide(true, 4*time);
		switchSide(false, 8*time);
		switchSide(false, 11*time);
		switchSide(true, 12*time);
		createPhase1();
		createPhase2();
		createPhase3();
		createPhase4();
		createPhase5();

		setArrowSpeed(6);
		createDiscoBall(DIRECTION.LEFT);
		createDiscoBall(DIRECTION.RIGHT);
		createDiscoDancer(DIRECTION.LEFT);
		createDiscoDancer(DIRECTION.RIGHT);
		litDiscoDancer(1*time);
		litDiscoDancer(11*time);
	}

	private int getY(int num) {
		int start = 10;
		int gap = 7;
		int puzzle = 120; // only because Puzzle start the music

		return getRow(start + num) + num * gap + puzzle;
	}

	private void createPhase1() {
		createArrow(DIRECTION.UP, getY(0), true);
		createArrow(DIRECTION.DOWN, getY(2), true);
		createArrow(DIRECTION.UP, getY(4), true);
		createArrow(DIRECTION.DOWN, getY(6), true);
		createArrow(DIRECTION.LEFT, getY(8), true);
		createArrow(DIRECTION.RIGHT, getY(9), true);
		createArrow(DIRECTION.LEFT, getY(10), true);
		createArrow(DIRECTION.RIGHT, getY(11), true);
		createArrow(DIRECTION.UP, getY(12), true);
		createArrow(DIRECTION.DOWN, getY(13), true);
		createArrow(DIRECTION.LEFT, getY(14), true);
		createArrow(DIRECTION.RIGHT, getY(15), true);
	}

	private void createPhase2() {
		createArrow(DIRECTION.UP, getY(16), true);
		createArrow(DIRECTION.DOWN, getY(17), true);
		createArrow(DIRECTION.RIGHT, getY(18), true);
		createArrow(DIRECTION.LEFT, getY(19), true);
		createArrow(DIRECTION.DOWN, getY(20), true);
		createArrow(DIRECTION.DOWN, getY(21), true);
		createArrow(DIRECTION.RIGHT, getY(22), true);
		createArrow(DIRECTION.LEFT, getY(23), true);

	}

	private void createPhase3() {
		createArrow(DIRECTION.UP, getY(24), true);
		createArrow(DIRECTION.DOWN, getY(24), true);
		createArrow(DIRECTION.UP, getY(25), true);
		createArrow(DIRECTION.DOWN, getY(25), true);
		createArrow(DIRECTION.LEFT, getY(26), true);
		createArrow(DIRECTION.RIGHT, getY(26), true);
		createArrow(DIRECTION.LEFT, getY(27), true);
		createArrow(DIRECTION.RIGHT, getY(27), true);
		createArrow(DIRECTION.UP, getY(28), true);
		createArrow(DIRECTION.DOWN, getY(28), true);
		createArrow(DIRECTION.LEFT, getY(29), true);
		createArrow(DIRECTION.RIGHT, getY(29), true);
	}

	private void createPhase4() {
		createArrow(DIRECTION.LEFT, getY(30), true);
		createArrow(DIRECTION.UP, getY(31), true);
		createArrow(DIRECTION.RIGHT, getY(32), true);
		createArrow(DIRECTION.DOWN, getY(33), true);
		createArrow(DIRECTION.LEFT, getY(34), true);
		createArrow(DIRECTION.UP, getY(35), true);
		createArrow(DIRECTION.RIGHT, getY(36), true);
		createArrow(DIRECTION.DOWN, getY(37), true);
		createArrow(DIRECTION.LEFT, getY(38), true);
		createArrow(DIRECTION.DOWN, getY(39), true);
		createArrow(DIRECTION.LEFT, getY(40), true);
		createArrow(DIRECTION.DOWN, getY(41), true);
		createArrow(DIRECTION.RIGHT, getY(42), true);
		createArrow(DIRECTION.DOWN, getY(43), true);
		createArrow(DIRECTION.RIGHT, getY(44), true);
		createArrow(DIRECTION.DOWN, getY(45), true);
	}

	private void createPhase5() {
		createArrow(DIRECTION.LEFT, getY(46), true);
		createArrow(DIRECTION.UP, getY(46), true);
		createArrow(DIRECTION.RIGHT, getY(46), true);
		createArrow(DIRECTION.DOWN, getY(46), true);
	}

}
