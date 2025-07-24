package com.sunsigne.reversedrebecca.object.puzzle.cowboy.living;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class CowboyRebeccaObject extends CowboyLivingObject {

	public CowboyRebeccaObject(Puzzle puzzle, boolean isCritical) {
		super(puzzle, isCritical);
	}

	////////// NAME ////////////

	protected String getName() {
		return "REBECCA";
	}

	@Override
	public String toString() {
		String pos = getRow(getX()) + "-" + getCol(getY());
		return "PUZZLE : " + getName() + " : " + pos;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TEXTURE ////////////

	private static LimitedCycloid<BufferedImage> images;

	@Override
	protected Cycloid<BufferedImage> getImages() {
		return images;
	}

	@Override
	public int getSheetRowCriterion() {
		return 2;
	}

	@Override
	protected void loadImages() {
		/*
		 * if (isCritical()) images = null; // ne marche pas tout à fait avec le surcrit mod, le critique est peut-être désactivé de base ?
		 */

		if (images != null)
			return;

		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cowboy_living");
		BufferedImage happy = getSheetSubImage(sheet, 1);
		BufferedImage sad = getSheetSubImage(sheet, 2);
		BufferedImage angry = getSheetSubImage(sheet, 3);
		BufferedImage furious = getSheetSubImage(sheet, 4);
		BufferedImage smirk = getSheetSubImage(sheet, 5);
		images = new LimitedCycloid<>(happy, sad, angry, furious, smirk);
	}

}
