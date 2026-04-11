package com.sunsigne.reversedrebecca.object.puzzle.cowboy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cowboy.CowboyPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class CowboyHoleObject extends PuzzleObject implements TickFree, SheetableImage, CollisionDetector {

	public CowboyHoleObject(Puzzle puzzle, boolean critical, int x, int y) {
		super(puzzle, critical, x, y);
	}

	////////// NAME ////////////

	protected String getName() {
		return "HOLE";
	}

	@Override
	public String toString() {
		String pos = getX() + "-" + getY();
		String overBadGuy = ((CowboyPuzzle) getPuzzle()).isWinning() ? "TRUE" : "FALSE";

		return "PUZZLE : " + getName() + " : " + "OVER BAD GUY" + " : " + overBadGuy + " / " + pos;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetSize() {
		return 2 * 16;
	}

	@Override
	public int getSheetColCriterion() {
		return 3;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "bomb_shoot");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
