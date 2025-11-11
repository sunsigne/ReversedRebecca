package com.sunsigne.reversedrebecca.object.puzzle.yy.strenght;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.YY;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class StrenghtLauncherObject extends StrenghPuzzleObject {

	public StrenghtLauncherObject(Puzzle puzzle, int puzzleSpeed) {
		super(puzzle, puzzleSpeed, 0, 0, 3 * Size.L, 3 * Size.L);
		loadLiving();
		loadAnimations();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : " + "LAUNCHER YY";
	}

	////////// POSITION ////////////

	@Override
	public void setX(int x) {
		super.setX(x);
		if (living != null)
			living.setX(x);
	}

	@Override
	public void setY(int y) {
		super.setY(y);
		if (living != null)
			living.setY(y);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		getAnimation().run();
	}

	////////// TEXTURE ////////////

	private LivingObject living;

	private void loadLiving() {
		living = new YY(0, 0);
		living.setFacing(DIRECTION.RIGHT);
		living.setBlockingPath(false);
	}

	private LivingAnimation throwingAnimation;

	private void loadAnimations() {
		int animation_time = 25 * getPuzzleSpeed();
		throwingAnimation = new LivingAnimation(living, animation_time, true, 11, 12);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {
		return throwingAnimation;
	}

	@Override
	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

}
