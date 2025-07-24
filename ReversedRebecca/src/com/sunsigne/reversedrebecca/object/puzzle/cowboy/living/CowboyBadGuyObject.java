package com.sunsigne.reversedrebecca.object.puzzle.cowboy.living;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyHatObject;
import com.sunsigne.reversedrebecca.object.puzzle.cowboy.CowboyHoleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cowboy.CowboyPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class CowboyBadGuyObject extends CowboyLivingObject implements CollisionReactor {

	public CowboyBadGuyObject(Puzzle puzzle, boolean isCritical) {
		super(puzzle, isCritical);
	}

	////////// NAME ////////////

	protected String getName() {
		return "BAD GUY";
	}

	@Override
	public String toString() {
		String pos = getRow(getX()) + "-" + getCol(getY());
		String holeOverThis = ((CowboyPuzzle) getPuzzle()).isWinning() ? "TRUE" : "FALSE";

		return "PUZZLE : " + getName() + " : " + "HOLE OVER HIM" + " : " + holeOverThis + " / " + pos;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}

	////////// TEXTURE ////////////

	private Cycloid<BufferedImage> images;

	@Override
	protected Cycloid<BufferedImage> getImages() {
		return images;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	protected void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cowboy_living");
		BufferedImage normal = getSheetSubImage(sheet, 1);
		BufferedImage smirk = getSheetSubImage(sheet, 2);
		BufferedImage shocked = getSheetSubImage(sheet, 3);
		images = new Cycloid<>(normal, smirk, shocked);
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
		CowboyPuzzle puzzle = (CowboyPuzzle) getPuzzle();
		if (puzzle.isWinning())
			return;

		if (detectorObject instanceof CowboyHoleObject == false)
			return;

		puzzle.setWinning(true);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "shoot_gun");
		CowboyHatObject hat = new CowboyHatObject(getPuzzle(), isCritical(), getX(), getY());
		LAYER.PUZZLE.addObject(hat);
	}

}
