package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import com.sunsigne.reversedrebecca.object.piranha.living.YY;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYBoss;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling;

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
	private LivingAnimation uppercutAnimation;
	private LivingAnimation dbzTpAnimation;

	///// orientable & not animated /////

	private LivingAnimation standingGoodAnimation;

	///// not orientable & animated /////

	private LivingAnimation tiredAnimation;
	private LivingAnimation tornadoAnimation;
	private LivingAnimation flexOneAnimation;
	private LivingAnimation flexTwoAnimation;
	private LivingAnimation flexThreeAnimation;
	private LivingAnimation flexFourAnimation;

	@Override
	protected void loadAnimations() {

		walkingGoodAnimation = new LivingAnimation(living, 15, true, 2, 3);
		pushUpAnimation = new LivingAnimation(living, 41, true, 4, 5);
		pushUpOneHandAnimation = new LivingAnimation(living, 41, true, 6, 7);
		throwingAnimation = new LivingAnimation(living, 11, true, 11, 12);
		punchingAnimation = new LivingAnimation(living, 10, true, 9, 10, 9, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12);
		uppercutAnimation = new LivingAnimation(living, 41, true, 11, 12, 12, 12, 13, 13, 13, 13, 4, 5, 4, 5, 6, 7, 6,
				7, 6, 7, 6, 7);
		dbzTpAnimation = new LivingAnimation(living, 4, true, 14, 15);

		standingGoodAnimation = new LivingAnimation(living, -1, true, 1);

		tiredAnimation = new LivingAnimation(living, 60, false, 1, 2);
		tornadoAnimation = new LivingAnimation(living, 3, false, 15, 16);
		flexOneAnimation = new LivingAnimation(living, 10, false, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4,
				3, 4, 3, 3, 3, 3, 4, 3, 4, 4, 3, 4, 3, 4);
		flexTwoAnimation = new LivingAnimation(living, 160, false, 5, 6);
		flexThreeAnimation = new LivingAnimation(living, 80, false, 8, 12);
		flexFourAnimation = new LivingAnimation(living, 6, false, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
				10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9, 10, 9);

	}

	////////// RENDER ////////////

	@Override
	protected LivingAnimation getAnimation() {
		switch (((DoubleYFeeling) living).getDoubleYCondition()) {
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
		case UPPERCUT:
			return uppercutAnimation;
		case DBZ_TP:
			return dbzTpAnimation;
		case FLEX_1:
			return flexOneAnimation;
		case FLEX_2:
			return flexTwoAnimation;
		case FLEX_3:
			return flexThreeAnimation;
		case FLEX_4:
			return flexFourAnimation;
		}

		return standingGoodAnimation;
	}

}
