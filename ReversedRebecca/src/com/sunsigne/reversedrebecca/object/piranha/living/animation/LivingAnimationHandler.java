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

		walkingGoodAnimation = new LivingAnimation(living, "walking_", 15, true);
		walkingGlassAnimation = new LivingAnimation(living, "var/glass/walking_", 15, true);
		walkingCupAnimation = new LivingAnimation(living, "var/cup/walking_", 15, true);
		standingSickAnimation = new LivingAnimation(living, "var/sick/standing_", 30, true);
//		walkingSickAnimation = new LivingAnimation(living, "var/sick/walking_", 15, true); // to create ?
		walkingSickAnimation = new LivingAnimation(living, "var/sick/standing_", 30, true);

		standingGoodAnimation = new LivingAnimation(living, "standing_", -1, true);
		standingGlassAnimation = new LivingAnimation(living, "var/glass/standing_", -1, true);
		standingCupAnimation = new LivingAnimation(living, "var/cup/standing_", -1, true);
		sitAnimation = new LivingAnimation(living, "var/sit/", -1, true);

		bedAnimation = new LivingAnimation(living, "var/fixed/bed", 59, false);
		cryAnimation = new LivingAnimation(living, "var/fixed/cry", 50, false);

		bathAnimation = new LivingAnimation(living, "var/fixed/bath", -1, false);
		koAnimation = new LivingAnimation(living, "var/fixed/ko", -1, false);
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

	public BufferedImage getHightlightImage() {
		return getAnimation().getHightlightFromImage(getImage());
	}

}
