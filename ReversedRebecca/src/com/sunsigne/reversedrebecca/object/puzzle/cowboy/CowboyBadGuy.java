package com.sunsigne.reversedrebecca.object.puzzle.cowboy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cowboy.CowboyPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class CowboyBadGuy extends PuzzleObject implements SheetableImage, TickFree, CollisionReactor {

	public CowboyBadGuy(Puzzle puzzle, boolean isCritical) {
		super(puzzle, isCritical, 0, 0, 3 * Size.XL, 3 * Size.XL);
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;
	private BufferedImage hatless_image;

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
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cowboy_badguy");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	public BufferedImage getHatlessImage() {
		if (hatless_image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cowboy_badguy");
			hatless_image = getSheetSubImage(sheet, 2);
		}
		return hatless_image;
	}

	//////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		BufferedImage img = getImage();
		if (isHatless)
			img = getHatlessImage();

		g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
	}

	//////// COLLISION ////////////
	
	private boolean isHatless;
	
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
		if (isHatless)
			return;

		if (detectorObject instanceof CowboyCursorObject == false)
			return;

		CowboyCursorObject cursor = (CowboyCursorObject) detectorObject;

		if (cursor.isCursorPressed()) {
			isHatless = true;
			new SoundTask().playSound(SOUNDTYPE.SOUND, "shoot_gun");
			CowboyPuzzle puzzle = (CowboyPuzzle) getPuzzle();
			puzzle.createHatObject(getX(), getY());
		}
	}

}
