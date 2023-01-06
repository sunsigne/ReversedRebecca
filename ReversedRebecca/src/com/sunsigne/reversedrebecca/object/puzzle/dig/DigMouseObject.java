package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.MouseObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class DigMouseObject extends DigPuzzleObject implements MouseObject {

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

	////////// TEXTURE ////////////

	public BufferedImage getImage() {
		return getPuzzle().getState().getImage();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// MOUSE ////////////

	@Override
	public MouseController getMouseController() {
		return null;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
