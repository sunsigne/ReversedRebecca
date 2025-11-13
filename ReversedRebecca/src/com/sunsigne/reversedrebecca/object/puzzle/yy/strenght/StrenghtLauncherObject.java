package com.sunsigne.reversedrebecca.object.puzzle.yy.strenght;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.YY;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class StrenghtLauncherObject extends StrenghPuzzleObject implements TickFree {

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

	////////// TEXTURE ////////////

	private LivingObject living;

	private void loadLiving() {
		living = new YY(0, 0);
		living.setFacing(DIRECTION.RIGHT);
		living.setBlockingPath(false);
	}

	private LivingAnimation holdingAnimation;
	private LivingAnimation thrownAnimation;

	private void loadAnimations() {
		holdingAnimation = new LivingAnimation(living, -1, true, 11);
		thrownAnimation = new LivingAnimation(living, -1, true, 12);
	}

	////////// RENDER ////////////

	public static boolean throwing = true;

	private LivingAnimation getAnimation() {
		if (throwing)
			return thrownAnimation;
		else
			return holdingAnimation;
	}

	@Override
	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

}
