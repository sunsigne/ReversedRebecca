package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class DuplicatingBombObject extends CrazyBombObject {

	public DuplicatingBombObject(Puzzle puzzle, boolean critical, int x, int y) {
		super(puzzle, critical, x, y);
		
		radCount[0] = new RandomGenerator().getIntBetween(2, 5);
		radCount[1] = new RandomGenerator().getIntBetween(2, 5);
	}

	@Override
	public void setExploded(boolean exploded) {
		super.setExploded(exploded);

		if (exploded && isCritical() == false)
			createCrazyBombs();
	}
	
	private int[] radCount = new int[2];
	
	public int getBonusBullet() {
		return radCount[0] + radCount[1];
	}

	private void createCrazyBombs() {
		CrazyBombObject[] bomb = new CrazyBombObject[2];

		for (int index = 0; index < 2; index++) {

			bomb[index] = new CrazyBombObject(getPuzzle(), false, getX(), getY());
			bomb[index].setMaxCount(radCount[index]);
			bomb[index].setCount(radCount[index]);

			LAYER.PUZZLE.addObject(bomb[index]);
		}

		// split trajectory
		if (bomb[0].getVelX() == bomb[1].getVelX())
			bomb[0].setVelX(-bomb[0].getVelX());
	}

	////////// NAME ////////////

	@Override
	protected String getName() {
		return "BOMB DUPLICATING";
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

}
