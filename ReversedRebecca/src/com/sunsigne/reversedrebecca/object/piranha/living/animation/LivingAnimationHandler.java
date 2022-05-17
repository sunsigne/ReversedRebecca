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

	///// orientable & animated/////

	private LivingAnimation walkingAnimation;
	private LivingAnimation walkingGlassAnimation;	
	private LivingAnimation walkingSickAnimation;
	private LivingAnimation standingSickAnimation;

	///// orientable & not animated /////

	private LivingAnimation standingAnimation;
	private LivingAnimation standingGlassAnimation;

	///// not orientable & animated /////

	private LivingAnimation bedAnimation;

	///// not orientable & not animated /////

	private LivingAnimation bathAnimation;
	private LivingAnimation koAnimation;

	private void loadAnimations() {

		///// orientable & animated/////

		walkingAnimation = new LivingAnimation(living, "walking_", 15, true);
		list.addObject(walkingAnimation);
		walkingGlassAnimation = new LivingAnimation(living, "var/glass/walking_", 15, true);
		list.addObject(walkingGlassAnimation);
//		walkingSickAnimation = new LivingAnimation(living, "var/sick/walking_", 15, true); // to create ?
		walkingSickAnimation = new LivingAnimation(living, "var/sick/standing_", 30, true);
		list.addObject(walkingSickAnimation);
		standingSickAnimation = new LivingAnimation(living, "var/sick/standing_", 30, true);
		list.addObject(standingSickAnimation);

		///// orientable & not animated /////

		standingAnimation = new LivingAnimation(living, "standing_", -1, true);
		list.addObject(standingAnimation);
		standingGlassAnimation = new LivingAnimation(living, "var/glass/standing_", -1, true);
		list.addObject(standingGlassAnimation);

		///// not orientable & animated /////
		
		bedAnimation = new LivingAnimation(living, "var/fixed/bed", 58, false);
		list.addObject(bedAnimation);

		///// not orientable & not animated /////
		
		bathAnimation = new LivingAnimation(living, "var/fixed/bath", -1, false);
		list.addObject(bathAnimation);
		koAnimation = new LivingAnimation(living, "var/fixed/ko", -1, false);
		list.addObject(koAnimation);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {

		switch (living.getCondition()) {

		case GOOD:
			return getGoodAnimation();

		case GLASS:
			return getGlassAnimation();
			
		case SICK:
			return getSickAnimation();

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

	private LivingAnimation getGlassAnimation() {
		if (living.isMotionless() || living.isStunned())
			return standingGlassAnimation;
		else
			return walkingGlassAnimation;
	}

	private LivingAnimation getSickAnimation() {
		if (living.isMotionless() || living.isStunned())
			return standingSickAnimation;
		else
			return walkingSickAnimation;
	}

	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

}
