package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;

public class LivingAnimationHandler {

	public LivingAnimationHandler(LivingObject living) {
		this.living = living;
		loadAnimations();
	}

	private LivingObject living;

	////////// TICK ////////////

	private LivingAnimation registeredAnimation;

	public void run() {

		if (registeredAnimation == getAnimation()) {
			getAnimation().run();
			return;
		}

		registeredAnimation = getAnimation();
		getAnimation().freeze();
	}

	////////// TEXTURE ////////////

	///// orientable & animated /////

	private LivingAnimation walkingGoodAnimation;
	private LivingAnimation walkingGlassAnimation;
	private LivingAnimation walkingCupAnimation;
	private LivingAnimation standingSickAnimation;
	private LivingAnimation walkingSickAnimation;

	///// orientable & not animated /////

	private LivingAnimation standingGoodAnimation;
	private LivingAnimation standingGlassAnimation;
	private LivingAnimation standingCupAnimation;
	private LivingAnimation sitAnimation;

	///// not orientable & animated /////

	private LivingAnimation bedAnimation;
	private LivingAnimation cryAnimation;

	///// not orientable & not animated /////

	private LivingAnimation bathAnimation;
	private LivingAnimation koAnimation;

	private void loadAnimations() {

		walkingGoodAnimation = new LivingAnimation(living, 15, true, 2, 3);
		walkingGlassAnimation = new LivingAnimation(living, 15, true, 5, 6);
		walkingCupAnimation = new LivingAnimation(living, 15, true, 8, 9);
		standingSickAnimation = new LivingAnimation(living, 30, true, 10, 11);
//		walkingSickAnimation = new LivingAnimation(living, 15, true, X, Y); // to create ?

		standingGoodAnimation = new LivingAnimation(living, -1, true, 1);
		standingGlassAnimation = new LivingAnimation(living, -1, true, 4);
		standingCupAnimation = new LivingAnimation(living, -1, true, 7);
		sitAnimation = new LivingAnimation(living, -1, true, 12);

		bedAnimation = new LivingAnimation(living, 59, false, 1, 2);
		cryAnimation = new LivingAnimation(living, 50, false, 3, 4);

		bathAnimation = new LivingAnimation(living, -1, false, 6);
		koAnimation = new LivingAnimation(living, -1, false, 5);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {
		switch (living.getCondition()) {
		case GOOD:
			return getStandingWalkingAnimation(standingGoodAnimation, walkingGoodAnimation);
		case GLASS:
			return getStandingWalkingAnimation(standingGlassAnimation, walkingGlassAnimation);
		case CUP:
			return getStandingWalkingAnimation(standingCupAnimation, walkingCupAnimation);
		case SICK:
			return getStandingWalkingAnimation(standingSickAnimation, walkingSickAnimation);
		case BATH:
			return bathAnimation;
		case BED:
			return bedAnimation;
		case CRY:
			return cryAnimation;
		case RELAX:
			return koAnimation; // yes, that's the same animation
		case SIT:
			return sitAnimation;
		case KO:
			return koAnimation;
		}

		return standingGoodAnimation;
	}

	private LivingAnimation getStandingWalkingAnimation(LivingAnimation standingAnimation,
			LivingAnimation walkingAnimation) {
		if (living.isWalkingInPlace())
			return walkingAnimation;
		else if (living.isMotionless() || living.isStunned())
			return standingAnimation;
		else
			return walkingAnimation;
	}

	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

	public BufferedImage getHighlightImage() {
		return getAnimation().getHighlightFromImage(getImage());
	}

}
