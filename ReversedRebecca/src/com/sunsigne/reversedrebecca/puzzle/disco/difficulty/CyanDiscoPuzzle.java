package com.sunsigne.reversedrebecca.puzzle.disco.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoDancerObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.disco.DiscoPuzzle;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class CyanDiscoPuzzle extends DiscoPuzzle {

	public CyanDiscoPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public DiscoDancerObject getDiscoDancer() {
		return new DiscoDancerObject(this, "rebecca");
	}

	@Override
	public void createPuzzle() {
		createPlayerArrows();
		createDiscoBall();
		createDiscoDancer(18 * Game.SEC - 10);

		createPhase1();
		createPhase2();
		createPhase3();
		createPhase4();
		createPhase5();

		setArrowSpeed(6);
	}

	private int getY(int num) {
		int start = 18;
		int gap = 7;
		int puzzle = - Size.XS; // only because Puzzle start the music
		
		return getRow(start + num) + num * gap + puzzle;
	}

	private void createPhase1() {
		createArrow(DIRECTION.LEFT, getY(0));
		createArrow(DIRECTION.LEFT, getY(2));
		createArrow(DIRECTION.RIGHT, getY(4));
		createArrow(DIRECTION.RIGHT, getY(6));
		createArrow(DIRECTION.DOWN, getY(8));
		createArrow(DIRECTION.DOWN, getY(10));
		createArrow(DIRECTION.UP, getY(12));
		createArrow(DIRECTION.UP, getY(14));
	}

	private void createPhase2() {
		createArrow(DIRECTION.LEFT, getY(16));
		createArrow(DIRECTION.LEFT, getY(17));
		createArrow(DIRECTION.LEFT, getY(18));
		createArrow(DIRECTION.LEFT, getY(19));
		createArrow(DIRECTION.RIGHT, getY(20));
		createArrow(DIRECTION.RIGHT, getY(21));
		createArrow(DIRECTION.RIGHT, getY(22));
		createArrow(DIRECTION.RIGHT, getY(23));
		createArrow(DIRECTION.UP, getY(24));
		createArrow(DIRECTION.UP, getY(25));
		createArrow(DIRECTION.DOWN, getY(26));
		createArrow(DIRECTION.DOWN, getY(27));
		createArrow(DIRECTION.LEFT, getY(28));
		createArrow(DIRECTION.RIGHT, getY(29));
		createArrow(DIRECTION.LEFT, getY(30));
		createArrow(DIRECTION.RIGHT, getY(31));
	}

	private void createPhase3() {
		createArrow(DIRECTION.LEFT, getY(32));
		createArrow(DIRECTION.LEFT, getY(34));
		createArrow(DIRECTION.RIGHT, getY(36));
		createArrow(DIRECTION.RIGHT, getY(38));
		createArrow(DIRECTION.UP, getY(40));
		createArrow(DIRECTION.DOWN, getY(42));
		createArrow(DIRECTION.LEFT, getY(44));
		createArrow(DIRECTION.RIGHT, getY(45));
		createArrow(DIRECTION.LEFT, getY(46));
		createArrow(DIRECTION.RIGHT, getY(47));
	}

	private void createPhase4() {
		createArrow(DIRECTION.LEFT, getY(48));
		createArrow(DIRECTION.LEFT, getY(49));
		createArrow(DIRECTION.RIGHT, getY(50));
		createArrow(DIRECTION.RIGHT, getY(51));
		createArrow(DIRECTION.UP, getY(52));
		createArrow(DIRECTION.UP, getY(53));
		createArrow(DIRECTION.UP, getY(54));
		createArrow(DIRECTION.UP, getY(55));
		createArrow(DIRECTION.DOWN, getY(56));
		createArrow(DIRECTION.DOWN, getY(57));
		createArrow(DIRECTION.DOWN, getY(58));
		createArrow(DIRECTION.DOWN, getY(59));
		createArrow(DIRECTION.LEFT, getY(60));
		createArrow(DIRECTION.LEFT, getY(61));
		createArrow(DIRECTION.RIGHT, getY(62));
		createArrow(DIRECTION.RIGHT, getY(63));
	}

	private void createPhase5() {
		createArrow(DIRECTION.UP, getY(64));
		createArrow(DIRECTION.DOWN, getY(64));
	}

}
