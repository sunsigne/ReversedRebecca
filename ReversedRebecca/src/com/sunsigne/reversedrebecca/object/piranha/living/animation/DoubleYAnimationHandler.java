package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import com.sunsigne.reversedrebecca.object.piranha.living.YY;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYBoss;

public class DoubleYAnimationHandler extends LivingAnimationHandler {

	public DoubleYAnimationHandler(YY yy) {
		super(yy);
	}

	public DoubleYAnimationHandler(DoubleYBoss doubleYBoss) {
		super(doubleYBoss);
	}

	////////// TEXTURE ////////////

	///// orientable & animated /////

	private LivingAnimation walkingGoodAnimation;
	private LivingAnimation pushUpAnimation;
	private LivingAnimation pushUpOneHandAnimation;
	private LivingAnimation throwingAnimation;
	private LivingAnimation punchingAnimation;

	///// orientable & not animated /////

	private LivingAnimation standingGoodAnimation;

	///// not orientable & animated /////

	private LivingAnimation tiredAnimation;
	private LivingAnimation tornadoAnimation;

	@Override
	protected void loadAnimations() {

		walkingGoodAnimation = new LivingAnimation(living, 15, true, 2, 3);
		pushUpAnimation = new LivingAnimation(living, 11, true, 4, 5);
		pushUpOneHandAnimation = new LivingAnimation(living, 11, true, 6, 7);
		throwingAnimation = new LivingAnimation(living, 11, true, 11, 12);
		punchingAnimation = new LivingAnimation(living, 10, true, 9, 10, 9, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12);

		standingGoodAnimation = new LivingAnimation(living, -1, true, 1);

		tiredAnimation = new LivingAnimation(living, 30, false, 1, 2);
		tornadoAnimation = new LivingAnimation(living, 3, false, 15, 16);

	}

	////////// RENDER ////////////

	@Override
	protected LivingAnimation getAnimation() {
		switch (((DoubleYBoss) living).getDoubleYCondition()) {
		case GOOD:
			return getStandingWalkingAnimation(standingGoodAnimation, walkingGoodAnimation);
		case TIRED:
			return tiredAnimation;
		case TORNADO:
			return tornadoAnimation;
		case PUSH_UP:
			return pushUpAnimation;
		case PUSH_UP_ONE_HAND:
			return pushUpOneHandAnimation;
		case THROWING:
			return throwingAnimation;
		case PUNCHING:
			return punchingAnimation;
		}

		return standingGoodAnimation;
	}

}
