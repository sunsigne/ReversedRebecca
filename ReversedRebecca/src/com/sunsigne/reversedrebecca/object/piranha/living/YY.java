package com.sunsigne.reversedrebecca.object.piranha.living;

import com.sunsigne.reversedrebecca.object.piranha.living.animation.DoubleYAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimationHandler;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling;

public class YY extends LivingObject implements DoubleYFeeling {

	public YY(int x, int y) {
		super("double-y", x, y);
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return 6 * getPixelSize();
	}

	@Override
	public int getHeight() {
		return 6 * getPixelSize();
	}

	////////// DOUBLE Y CONDITION ////////////

	private DOUBLE_Y_CONDITION doubleYCondition = DOUBLE_Y_CONDITION.GOOD;

	@Override
	public DOUBLE_Y_CONDITION getDoubleYCondition() {
		return doubleYCondition;
	}

	@Override
	public void setDoubleYCondition(DOUBLE_Y_CONDITION doubleYCondition) {
		this.doubleYCondition = doubleYCondition;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		animation.run();
	}

	////////// TEXTURE ////////////

	@Override
	protected void loadAnimationHandler(LivingAnimationHandler animationHandler) {
		super.loadAnimationHandler(new DoubleYAnimationHandler(this));
	}

	////////// COLLISION ////////////

	@Override
	public int getPixelSize() {
		return 24;
	}

}
