package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class LivingAnimationHandler {

	public LivingAnimationHandler(LivingObject living) {
		this.living = living;
		loadAnimations();
	}

	private LivingObject living;

	////////// TICK ////////////

	private GameLimitedList<LivingAnimation> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public void run() {

		list.getList().forEach(animation -> {
			if (animation != getAnimation())
				animation.freeze();
		});

		getAnimation().run();
	}

	////////// TEXTURE ////////////

	///// animated & orientable /////

	private LivingAnimation walkingAnimation;
	private LivingAnimation sickAnimation;

	///// animated & not orientable /////

	private LivingAnimation bedAnimation;

	///// not animated & orientable /////

	private LivingAnimation standingAnimation;

	///// not animated & not orientable /////

	private LivingAnimation bathAnimation;
	private LivingAnimation koAnimation;

	private void loadAnimations() {

		///// animated & orientable /////

		walkingAnimation = new LivingAnimation(living, "walking", 15, true);
		list.addObject(walkingAnimation);
		sickAnimation = new LivingAnimation(living, "sick", 30, true);
		list.addObject(sickAnimation);

		///// animated & not orientable /////

		bathAnimation = new LivingAnimation(living, "bath", -1, false);
		list.addObject(bathAnimation);

		///// not animated & orientable /////

		standingAnimation = new LivingAnimation(living, "standing", -1, true);
		list.addObject(standingAnimation);

		///// not animated & not orientable /////

		bedAnimation = new LivingAnimation(living, "bed", 58, false);
		list.addObject(bedAnimation);
		koAnimation = new LivingAnimation(living, "ko", -1, false);
		list.addObject(koAnimation);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {

		switch (living.getCondition()) {

		case GOOD:
			return getGoodAnimation();

		case SICK:
			return sickAnimation;

		case BATH:
			return bathAnimation;

		case BED:
			return bedAnimation;

		case KO:
			return koAnimation;
		}

		return standingAnimation;
	}

	private LivingAnimation getGoodAnimation() {
		if (living.isMotionless() || living.isStunned())
			return standingAnimation;
		else
			return walkingAnimation;
	}

	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

}
