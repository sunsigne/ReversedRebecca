package com.sunsigne.reversedrebecca.object.puzzle.bombkey;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PointerPuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class PointerKeyObject extends PointerPuzzleObject {

	public PointerKeyObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, Size.M / 2, Size.M / 2);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "key");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {
		if (isClickable() == false)
			return;

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
