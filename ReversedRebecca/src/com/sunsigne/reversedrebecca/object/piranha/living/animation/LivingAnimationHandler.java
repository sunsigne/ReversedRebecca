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

	private LivingAnimation standingAnimation;
	private LivingAnimation walkingAnimation;
	private LivingAnimation sickAnimation;

	private void loadAnimations() {

		standingAnimation = new StandingAnimation(living);
		list.addObject(standingAnimation);

		walkingAnimation = new WalkingAnimation(living);
		list.addObject(walkingAnimation);

		sickAnimation = new SickAnimation(living);
		list.addObject(sickAnimation);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {

		switch (living.getCondition()) {

		case GOOD:
			if (living.isMotionless())
				return standingAnimation;
			else
				return walkingAnimation;

		case SICK:
			return sickAnimation;
		}

		return standingAnimation;
	}

	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

}
