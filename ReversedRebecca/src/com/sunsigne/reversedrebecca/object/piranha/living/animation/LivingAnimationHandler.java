package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.KOAnimation;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.StandingAnimation;
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

	private LivingAnimation standingAnimation;
	private LivingAnimation koAnimation;

	private LivingAnimation walkingAnimation;
	private LivingAnimation sickAnimation;

	private void loadAnimations() {

		standingAnimation = new StandingAnimation(living);
		list.addObject(standingAnimation);

		koAnimation = new KOAnimation(living);
		list.addObject(koAnimation);

		walkingAnimation = new WalkingAnimation(living);
		list.addObject(walkingAnimation);

		sickAnimation = new SickAnimation(living);
		list.addObject(sickAnimation);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {

		switch (living.getCondition()) {

		case GOOD:
			return getGoodAnimation();

		case SICK:
			return sickAnimation;

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
