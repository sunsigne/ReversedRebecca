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

	///// orientable & animated /////

	private LivingAnimation walkingGoodAnimation;
	private LivingAnimation walkingGlassAnimation;
	private LivingAnimation standingSickAnimation;
	private LivingAnimation walkingSickAnimation;

	///// orientable & not animated /////

	private LivingAnimation standingGoodAnimation;
	private LivingAnimation standingGlassAnimation;
	private LivingAnimation sitToiletAnimation;

	///// not orientable & animated /////

	private LivingAnimation bedAnimation;

	///// not orientable & not animated /////

	private LivingAnimation bathAnimation;
	private LivingAnimation koAnimation;

	private void loadAnimations() {

		walkingGoodAnimation = new LivingAnimation(living, "walking_", 15, true);
		walkingGlassAnimation = new LivingAnimation(living, "var/glass/walking_", 15, true);
		standingSickAnimation = new LivingAnimation(living, "var/sick/standing_", 30, true);
//		walkingSickAnimation = new LivingAnimation(living, "var/sick/walking_", 15, true); // to create ?
		walkingSickAnimation = new LivingAnimation(living, "var/sick/standing_", 30, true);

		standingGoodAnimation = new LivingAnimation(living, "standing_", -1, true);
		standingGlassAnimation = new LivingAnimation(living, "var/glass/standing_", -1, true);
		sitToiletAnimation = new LivingAnimation(living, "var/sit/toilet_", -1, true);
		
		bedAnimation = new LivingAnimation(living, "var/fixed/bed", 58, false);

		bathAnimation = new LivingAnimation(living, "var/fixed/bath", -1, false);
		koAnimation = new LivingAnimation(living, "var/fixed/ko", -1, false);

		list.addObject(walkingGoodAnimation);
		list.addObject(walkingGlassAnimation);
		list.addObject(standingSickAnimation);
		list.addObject(walkingSickAnimation);

		list.addObject(standingGoodAnimation);
		list.addObject(standingGlassAnimation);

		list.addObject(bedAnimation);

		list.addObject(bathAnimation);
		list.addObject(koAnimation);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {
		switch (living.getCondition()) {
		case GOOD:
			return getStandingWalkingAnimation(standingGoodAnimation, walkingGoodAnimation);
		case GLASS:
			return getStandingWalkingAnimation(standingGlassAnimation, walkingGlassAnimation);
		case SICK:
			return getStandingWalkingAnimation(standingSickAnimation, walkingSickAnimation);
		case BATH:
			return bathAnimation;
		case BED:
			return bedAnimation;
		case TOILET:
			return sitToiletAnimation;
		case KO:
			return koAnimation;
		}

		return standingGoodAnimation;
	}

	private LivingAnimation getStandingWalkingAnimation(LivingAnimation standingAnimation,
			LivingAnimation walkingAnimation) {
		if (living.isMotionless() || living.isStunned())
			return standingAnimation;
		else
			return walkingAnimation;
	}

	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

}
