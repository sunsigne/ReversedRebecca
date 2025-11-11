package com.sunsigne.reversedrebecca.object.puzzle.yy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.YY;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class LauncherYYObject extends PuzzleObject {

	public LauncherYYObject(Puzzle puzzle) {
		super(puzzle, false, 0, 0, 2 * Size.XL, 2 * Size.XL);
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

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
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
		throwingAnimation = new LivingAnimation(living, 50, true, 11, 12);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {
		return throwingAnimation;
	}

	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
