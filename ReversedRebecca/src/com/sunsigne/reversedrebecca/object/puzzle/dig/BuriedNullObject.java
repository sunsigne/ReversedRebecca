package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class BuriedNullObject extends BuriedObject {

	public BuriedNullObject(Puzzle puzzle, int size) {
		super(puzzle, size);
	}

	////////// NAME ////////////

	@Override
	protected String getName() {
		return "NULL";
	}

	////////// TEXTURE ////////////

	@Override
	public BufferedImage getImage() {
		return null;
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {

	}

}
