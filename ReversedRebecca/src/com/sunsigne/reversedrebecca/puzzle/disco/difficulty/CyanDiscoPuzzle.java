package com.sunsigne.reversedrebecca.puzzle.disco.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.disco.DiscoPuzzle;

public class CyanDiscoPuzzle extends DiscoPuzzle {

	public CyanDiscoPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createPlayerArrows();

		createPhase1(12);
		createPhase2(12 + 15 + 9);

		setArrowSpeed(4);
	}

	private void createPhase1(int start) {
		createArrow(DIRECTION.LEFT, start);
		createArrow(DIRECTION.LEFT, start + 2);
		createArrow(DIRECTION.RIGHT, start + 4);
		createArrow(DIRECTION.RIGHT, start + 6);
		createArrow(DIRECTION.DOWN, start + 8);
		createArrow(DIRECTION.UP, start + 10);

		createArrow(DIRECTION.LEFT, start + 11);
		createArrow(DIRECTION.RIGHT, start + 12);
		createArrow(DIRECTION.DOWN, start + 13);
		createArrow(DIRECTION.UP, start + 14);
		createArrow(DIRECTION.DOWN, start + 15);
	}

	private void createPhase2(int start) {
		createArrow(DIRECTION.LEFT, start);
		createArrow(DIRECTION.LEFT, start + 2);
		createArrow(DIRECTION.RIGHT, start + 4);
		createArrow(DIRECTION.UP, start + 6);

		createArrow(DIRECTION.LEFT, start + 8);
		createArrow(DIRECTION.RIGHT, start + 8);
		createArrow(DIRECTION.LEFT, start + 10);
		createArrow(DIRECTION.RIGHT, start + 10);
		createArrow(DIRECTION.LEFT, start + 12);
		createArrow(DIRECTION.UP, start + 12);
		createArrow(DIRECTION.RIGHT, start + 14);
		createArrow(DIRECTION.UP, start + 14);
		createArrow(DIRECTION.UP, start + 16);
		createArrow(DIRECTION.DOWN, start + 16);
	}

}
