package com.sunsigne.reversedrebecca.object.puzzle.yy.strenght;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class StrenghPuzzleObject extends PuzzleObject {

	public StrenghPuzzleObject(Puzzle puzzle, int puzzleSpeed, int x, int y) {
		this(puzzle, puzzleSpeed, x, y, 2 * Size.L, 2 * Size.L);
	}

	public StrenghPuzzleObject(Puzzle puzzle, int puzzleSpeed, int x, int y, int w, int h) {
		super(puzzle, false, x, y, w, h);
		this.puzzleSpeed = puzzleSpeed;
	}

	////////// SPEED ////////////

	private int puzzleSpeed;

	public int getPuzzleSpeed() {
		return puzzleSpeed;
	}

	public void setPuzzleSpeed(int puzzleSpeed) {
		this.puzzleSpeed = puzzleSpeed;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// RENDER ////////////

	public abstract BufferedImage getImage();

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
