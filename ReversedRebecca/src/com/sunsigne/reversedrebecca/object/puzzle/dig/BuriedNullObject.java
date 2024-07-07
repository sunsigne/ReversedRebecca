package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class BuriedNullObject extends BuriedObject {

	public BuriedNullObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "NULL";
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {

	}

}
