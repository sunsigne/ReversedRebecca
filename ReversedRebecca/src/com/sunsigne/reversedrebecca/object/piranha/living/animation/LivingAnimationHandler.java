package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.StandingAnimation;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.fixed.BathAnimation;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.fixed.BedAnimation;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.constant.fixed.KOAnimation;
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
	
	///// animation /////
	
	private LivingAnimation walkingAnimation;
	private LivingAnimation sickAnimation;
	
	///// constant /////
	
	private LivingAnimation standingAnimation;
	
	///// fixed /////
	
	private LivingAnimation bathAnimation;
	private LivingAnimation bedAnimation;
	private LivingAnimation koAnimation;

	private void loadAnimations() {

		///// animation /////
		
		walkingAnimation = new WalkingAnimation(living);
		list.addObject(walkingAnimation);
		sickAnimation = new SickAnimation(living);
		list.addObject(sickAnimation);		
		
		///// constant /////
		
		standingAnimation = new StandingAnimation(living);
		list.addObject(standingAnimation);
		
		///// fixed /////
		
		bathAnimation = new BathAnimation(living);
		list.addObject(bathAnimation);		
		bedAnimation = new BedAnimation(living);
		list.addObject(bedAnimation);		
		koAnimation = new KOAnimation(living);
		list.addObject(koAnimation);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {

		switch (living.getCondition()) {

		case GOOD:
			return getGoodAnimation();

		case SICK:
			return sickAnimation;

		case BATH :
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
