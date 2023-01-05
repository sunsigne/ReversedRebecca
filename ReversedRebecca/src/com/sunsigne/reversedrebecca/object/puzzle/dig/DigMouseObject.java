package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class DigMouseObject extends DigPuzzleObject {

	public DigMouseObject(Puzzle puzzle, int w, int h) {
		super(puzzle, false, 0, 0, w, h);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : DIG HAND : " + getPuzzle().getState();
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
		return getPuzzle().getState().getImage();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
