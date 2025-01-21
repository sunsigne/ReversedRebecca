package com.sunsigne.reversedrebecca.object.puzzle.key;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class MovingWallPuzzleObject extends PuzzleObject implements CollisionReactor {

	private int speed = Size.XS / 8;

	public MovingWallPuzzleObject(Puzzle puzzle, BufferedImage image, int x, int y) {
		super(puzzle, false, x, y);
		this.image = image;
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : MOVING WALL";
		return clazz + " : " + getRow(getX()) + "-" + getCol(getY());
	}
	
	////////// PHYSICS ////////////
	
	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}
	
	////////// TICK ////////////

	private final int ymin = getPuzzle().getRow(1);
	private final int ymax = getPuzzle().getRow(6);

	@Override
	public void tick() {

		// goes up and down
		if (getY() >= ymax)
			setVelY(-speed);
		if (getY() <= ymin)
			setVelY(speed);
	}

	////////// RENDER ////////////

	private BufferedImage image;

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> getPuzzle().closePuzzle(false));
	}

}
