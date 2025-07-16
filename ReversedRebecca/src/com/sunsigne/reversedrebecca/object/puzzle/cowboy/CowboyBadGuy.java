package com.sunsigne.reversedrebecca.object.puzzle.cowboy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class CowboyBadGuy extends PuzzleObject implements SheetableImage, TickFree, CollisionReactor {

	public CowboyBadGuy(Puzzle puzzle, boolean isActive, int x, int y, int w, int h) {
		super(puzzle, isActive, x, y, w, h);
	}

	public CowboyBadGuy(Puzzle puzzle, boolean isActive) {
		this(puzzle, isActive, 0, 0, 2 * Size.XL, 2 * Size.XL);
	}

	//////// COWBOY ////////////

	private boolean isActive;

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetSize() {
		return 2 * 32;
	}

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	private BufferedImage image;

	public BufferedImage getImage() {

		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "bomb_bomb");
			image = getSheetSubImage(sheet);
		}

		if (!isActive) {
			BufferedImage deadBadGuyImage = new ImageTask().loadImage("textures/puzzle/" + "bomb_explosion");
			image = getSheetSubImage(deadBadGuyImage);
		}

		return image;
	}

	//////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	//////// COLLISION ////////////

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
		if (isActive == false)
			return;
		
		if ((detectorObject instanceof CowboyCursorObject) == false)
			return;

		CowboyCursorObject cursor = (CowboyCursorObject) detectorObject;

		if (cursor.isCursorPressed()) {
			isActive = false;
		}
	}

}
