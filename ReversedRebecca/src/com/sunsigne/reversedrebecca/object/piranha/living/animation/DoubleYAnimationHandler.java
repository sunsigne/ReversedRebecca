package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYBoss;

public class DoubleYAnimationHandler extends LivingAnimationHandler {

	public DoubleYAnimationHandler(DoubleYBoss doubleYBoss) {
		super(doubleYBoss);
	}

	////////// TEXTURE ////////////

	///// orientable & animated /////

	private LivingAnimation walkingGoodAnimation;
	private LivingAnimation walkingGlassAnimation;

	///// orientable & not animated /////

	private LivingAnimation standingGoodAnimation;
	private LivingAnimation standingGlassAnimation;

	@Override
	protected void loadAnimations() {

		walkingGoodAnimation = new LivingAnimation(living, 15, true, 2, 3);
		walkingGlassAnimation = new LivingAnimation(living, 15, true, 5, 6);

		standingGoodAnimation = new LivingAnimation(living, -1, true, 1);
		standingGlassAnimation = new LivingAnimation(living, -1, true, 4);

	}

	////////// RENDER ////////////

	@Override
	protected LivingAnimation getAnimation() {
		switch (((DoubleYBoss) living).getDoubleYCondition()) {
		case GOOD:
			return getStandingWalkingAnimation(standingGoodAnimation, walkingGoodAnimation);
		case PUSH_UP:
			return getStandingWalkingAnimation(standingGlassAnimation, walkingGlassAnimation);
		}

		return standingGoodAnimation;
	}

}
