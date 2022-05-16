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

	private GameLimitedList<NeoLivingAnimation> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public void run() {

		list.getList().forEach(animation -> {
			if (animation != getAnimation())
				animation.freeze();
		});

		getAnimation().run();
	}

	////////// TEXTURE ////////////

	///// animated & orientable /////

	private NeoLivingAnimation walkingAnimation;
	private NeoLivingAnimation sickAnimation;

	///// animated & not orientable /////

	private NeoLivingAnimation bedAnimation;

	///// not animated & orientable /////

	private NeoLivingAnimation standingAnimation;

	///// not animated & not orientable /////

	private NeoLivingAnimation bathAnimation;
	private NeoLivingAnimation koAnimation;

	private void loadAnimations() {

		///// animated & orientable /////

		walkingAnimation = new NeoLivingAnimation(living, "walking", 15, true);
		list.addObject(walkingAnimation);
		sickAnimation = new NeoLivingAnimation(living, "sick", 30, true);
		list.addObject(sickAnimation);

		///// animated & not orientable /////

		bathAnimation = new NeoLivingAnimation(living, "bath", -1, false);
		list.addObject(bathAnimation);

		///// not animated & orientable /////

		standingAnimation = new NeoLivingAnimation(living, "standing", -1, true);
		list.addObject(standingAnimation);

		///// not animated & not orientable /////

		bedAnimation = new NeoLivingAnimation(living, "bed", 58, false);
		list.addObject(bedAnimation);
		koAnimation = new NeoLivingAnimation(living, "ko", -1, false);
		list.addObject(koAnimation);
	}

	////////// RENDER ////////////

	private NeoLivingAnimation getAnimation() {

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

	private NeoLivingAnimation getGoodAnimation() {
		if (living.isMotionless() || living.isStunned())
			return standingAnimation;
		else
			return walkingAnimation;
	}

	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

}
