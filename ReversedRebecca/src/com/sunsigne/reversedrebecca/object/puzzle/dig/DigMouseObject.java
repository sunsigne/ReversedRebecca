package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class DigMouseObject extends PuzzleObject {

	public DigMouseObject(Puzzle puzzle, int size) {
		super(puzzle, false, 0, 0, size * 3 / 4, size * 3 / 4);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		DigPuzzle puzzle = (DigPuzzle) getPuzzle();
		return "PUZZLE : DIG HAND : " + puzzle.getState();
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		int mouseX = new MousePos().get()[0] - getWidth() / 2;
		int mouseY = new MousePos().get()[1] - getHeight() / 2;
		followMouse(mouseX, mouseY);
	}

	private void followMouse(int mouseX, int mouseY) {
		setX(mouseX);
		setY(mouseY);
	}

	////////// TEXTURE ////////////

	public BufferedImage getImage() {
		DigPuzzle puzzle = (DigPuzzle) getPuzzle();
		return puzzle.getState().getImage();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
