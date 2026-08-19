package com.sunsigne.reversedrebecca.puzzle.disco.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoDancerObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
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
			return new DiscoDancerObject(this, "stephabrina", true);

		secondDancer = true;
		return new DiscoDancerObject(this, "rebecca", false);
	}

	@Override
	public void createPuzzle() {
		createPlayerArrows(DIRECTION.UP);
		createPhase1(0);
		createPhase1(65);
		createPhase1(155);
		createPhase2(225);
		createPhase2(257);
		createPhase1(20 + 289);
		createPhase2(20 + 347);
		createPhase2(20 + 379);
		createPhase2(20 + 411);
		createPhase2(20 + 443);
		createPhase2(20 + 475);
		setArrowSpeed(0);

		new GameTimer(15, true, () -> createPuzzleWithDelay());
	}

	private void createPuzzleWithDelay() {
		int time = 6 * Game.SEC;

		int dancer = 1;
		int balls = 2;
		int everything = 3;

		// phase 1
		setArrowSpeed(6);
		switchSide(dancer, 2 * time);
		switchSide(balls, 4 * time);
		new GameTimer((int) (24.2f * (float) Game.SEC), true, () -> setArrowSpeed(12));
		new GameTimer(6 * time, true, () -> litDiscoDancer(0));
		switchSide(everything, 6 * time);
		new GameTimer(38 * Game.SEC, true, () -> setArrowSpeed(18));

		// phase 2
		new GameTimer((int) (48.5f * (float) Game.SEC), true, () -> setArrowSpeed(6));
		switchSide(dancer, 8 * time);
		switchSide(balls, 11 * time);
		new GameTimer(11 * time, true, () -> litDiscoDancer(0));
		switchSide(everything, 12 * time);

		// phase 3
		new GameTimer(75 * Game.SEC, true, () -> setArrowWallDelay(90));
		new GameTimer(13 * time, true, () -> {
			setArrowSpeed(12);
			setPlayerArrowEnlargedHitboxes(true);
		});
		switchSide(dancer, 15 * time);
		switchSide(balls, 16 * time);
		new GameTimer(16 * time, true, () -> {
			setArrowWallDelay(45);
			setArrowSpeed(2, true);
			setArrowSpeed(18);
		});
		switchSide(everything, 17 * time);
		new GameTimer(17 * time, true, () -> litDiscoDancer(0));
		switchSide(everything, 18 * time);

		createDiscoBall(DIRECTION.LEFT);
		createDiscoBall(DIRECTION.RIGHT);
		createDiscoDancer(DIRECTION.LEFT);
		createDiscoDancer(DIRECTION.RIGHT);
	}

	private int getY(int num) {
		int start = 9;
		int gap = 7;
		int puzzle = 120; // only because Puzzle start the music

		return getRow(start + num) + num * gap + puzzle;
	}

	private void createPhase1(int delay) {
		createArrow(DIRECTION.LEFT, getY(delay + 0));
		createArrow(DIRECTION.UP, getY(delay + 2), true);
		createArrow(DIRECTION.RIGHT, getY(delay + 4));
		createArrow(DIRECTION.DOWN, getY(delay + 6), true);
		createArrow(DIRECTION.LEFT, getY(delay + 8));
		createArrow(DIRECTION.RIGHT, getY(delay + 8), true);
		createArrow(DIRECTION.LEFT, getY(delay + 10));
		createArrow(DIRECTION.RIGHT, getY(delay + 10), true);
		createArrow(DIRECTION.LEFT, getY(delay + 12), true);
		createArrow(DIRECTION.RIGHT, getY(delay + 12));
		createArrow(DIRECTION.UP, getY(delay + 14), true);
		createArrow(DIRECTION.DOWN, getY(delay + 14));
		createArrow(DIRECTION.UP, getY(delay + 16));
		createArrow(DIRECTION.DOWN, getY(delay + 16), true);
		createArrow(DIRECTION.LEFT, getY(delay + 18));
		createArrow(DIRECTION.RIGHT, getY(delay + 18), true);
		createArrow(DIRECTION.UP, getY(delay + 20), true);
		createArrow(DIRECTION.DOWN, getY(delay + 20));
		createArrow(DIRECTION.LEFT, getY(delay + 22));
		createArrow(DIRECTION.UP, getY(delay + 24), true);
		createArrow(DIRECTION.RIGHT, getY(delay + 26));
		createArrow(DIRECTION.DOWN, getY(delay + 28), true);
		createArrow(DIRECTION.LEFT, getY(delay + 30), true);
		createArrow(DIRECTION.UP, getY(delay + 32));
		createArrow(DIRECTION.RIGHT, getY(delay + 34), true);
		createArrow(DIRECTION.DOWN, getY(delay + 36));
		createArrow(DIRECTION.LEFT, getY(delay + 38));
		createArrow(DIRECTION.RIGHT, getY(delay + 38), true);
		createArrow(DIRECTION.LEFT, getY(delay + 40), true);
		createArrow(DIRECTION.RIGHT, getY(delay + 40));
		createArrow(DIRECTION.LEFT, getY(delay + 42));
		createArrow(DIRECTION.UP, getY(delay + 42));
		createArrow(DIRECTION.RIGHT, getY(delay + 42), true);
		createArrow(DIRECTION.DOWN, getY(delay + 42), true);
		createArrow(DIRECTION.LEFT, getY(delay + 44), true);
		createArrow(DIRECTION.UP, getY(delay + 44));
		createArrow(DIRECTION.RIGHT, getY(delay + 44));
		createArrow(DIRECTION.DOWN, getY(delay + 44), true);
		createArrow(DIRECTION.LEFT, getY(delay + 46), true);
		createArrow(DIRECTION.UP, getY(delay + 47));
		createArrow(DIRECTION.RIGHT, getY(delay + 48), true);
		createArrow(DIRECTION.DOWN, getY(delay + 49), true);
		createArrow(DIRECTION.LEFT, getY(delay + 50));
		createArrow(DIRECTION.UP, getY(delay + 51), true);
		createArrow(DIRECTION.RIGHT, getY(delay + 52));
		createArrow(DIRECTION.DOWN, getY(delay + 54), true);
		createArrow(DIRECTION.DOWN, getY(delay + 56));
	}

	private void createPhase2(int delay) {
		createArrow(DIRECTION.LEFT, getY(delay + 0));
		createArrow(DIRECTION.UP, getY(delay + 2), true);
		createArrow(DIRECTION.RIGHT, getY(delay + 4));
		createArrow(DIRECTION.DOWN, getY(delay + 6), true);
		createArrow(DIRECTION.LEFT, getY(delay + 8));
		createArrow(DIRECTION.RIGHT, getY(delay + 10), true);
		createArrow(DIRECTION.UP, getY(delay + 12));
		createArrow(DIRECTION.DOWN, getY(delay + 14), true);
		createArrow(DIRECTION.LEFT, getY(delay + 16));
		createArrow(DIRECTION.RIGHT, getY(delay + 16), true);
		createArrow(DIRECTION.LEFT, getY(delay + 17));
		createArrow(DIRECTION.RIGHT, getY(delay + 17), true);
		createArrow(DIRECTION.UP, getY(delay + 18));
		createArrow(DIRECTION.LEFT, getY(delay + 18), true);
		createArrow(DIRECTION.DOWN, getY(delay + 19));
		createArrow(DIRECTION.UP, getY(delay + 19), true);
		createArrow(DIRECTION.LEFT, getY(delay + 20));
		createArrow(DIRECTION.DOWN, getY(delay + 20), true);
		createArrow(DIRECTION.RIGHT, getY(delay + 21));
		createArrow(DIRECTION.LEFT, getY(delay + 21), true);
		createArrow(DIRECTION.UP, getY(delay + 22));
		createArrow(DIRECTION.RIGHT, getY(delay + 22), true);
		createArrow(DIRECTION.DOWN, getY(delay + 23));
		createArrow(DIRECTION.UP, getY(delay + 23), true);
		createArrow(DIRECTION.UP, getY(delay + 24));
		createArrow(DIRECTION.DOWN, getY(delay + 24), true);
		createArrow(DIRECTION.UP, getY(delay + 25));
		createArrow(DIRECTION.RIGHT, getY(delay + 25), true);
		createArrow(DIRECTION.LEFT, getY(delay + 26));
		createArrow(DIRECTION.RIGHT, getY(delay + 26), true);
		createArrow(DIRECTION.DOWN, getY(delay + 27));
		createArrow(DIRECTION.RIGHT, getY(delay + 27), true);
		createArrow(DIRECTION.LEFT, getY(delay + 28));
		createArrow(DIRECTION.UP, getY(delay + 29), true);
		createArrow(DIRECTION.RIGHT, getY(delay + 30));
		createArrow(DIRECTION.DOWN, getY(delay + 31), true);
	}

}
