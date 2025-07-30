package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYBoss;

public class DoubleYAnimationHandler extends LivingAnimationHandler {

	public DoubleYAnimationHandler(DoubleYBoss doubleYBoss) {
		super(doubleYBoss);
	}

	////////// TEXTURE ////////////

	///// orientable & animated /////

	private LivingAnimation walkingGoodAnimation;
	private LivingAnimation pushUpAnimation;
	private LivingAnimation pushUpOneHandAnimation;

	///// orientable & not animated /////

	private LivingAnimation standingGoodAnimation;

	///// not orientable & animated /////

	private LivingAnimation tiredAnimation;
	
	@Override
	protected void loadAnimations() {

		walkingGoodAnimation = new LivingAnimation(living, 15, true, 2, 3);
		pushUpAnimation = new LivingAnimation(living, 11, true, 4, 5);
		pushUpOneHandAnimation = new LivingAnimation(living, 11, true, 6, 7);

		standingGoodAnimation = new LivingAnimation(living, -1, true, 1);
		
		tiredAnimation = new LivingAnimation(living, 30, false, 1, 2);

	}

	////////// RENDER ////////////

	@Override
	protected LivingAnimation getAnimation() {
		switch (((DoubleYBoss) living).getDoubleYCondition()) {
		case GOOD:
			return getStandingWalkingAnimation(standingGoodAnimation, walkingGoodAnimation);
		case TIRED:
			return tiredAnimation; 
		case PUSH_UP:
			return pushUpAnimation;
		case PUSH_UP_ONE_HAND:
			return pushUpOneHandAnimation; 
		}

		return standingGoodAnimation;
	}

}
