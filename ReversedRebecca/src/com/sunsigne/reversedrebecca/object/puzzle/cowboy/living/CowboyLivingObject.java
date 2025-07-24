package com.sunsigne.reversedrebecca.object.puzzle.cowboy.living;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class CowboyLivingObject extends PuzzleObject implements SheetableImage, TickFree {

	public CowboyLivingObject(Puzzle puzzle, boolean isCritical) {
		super(puzzle, isCritical, 0, 0, 4 * Size.XL, 4 * Size.XL);
		loadImages();
	}

	////////// TEXTURE ////////////

	protected abstract Cycloid<BufferedImage> getImages();

	public void cycle() {
		getImages().cycle();
	}

	@Override
	public int getSheetWidth() {
		return 144;
	}

	@Override
	public int getSheetHeight() {
		return 171;
	}

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	protected abstract void loadImages();

	//////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImages().getState(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
