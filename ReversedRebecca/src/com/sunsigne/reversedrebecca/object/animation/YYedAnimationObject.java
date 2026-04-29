package com.sunsigne.reversedrebecca.object.animation;

import com.sunsigne.reversedrebecca.object.GoalObject;

public class YYedAnimationObject extends AnimationObject {

	public YYedAnimationObject(int x, int y, GoalObject target) {
		super(x, y);
		GoalObject thiz = new GoalObject(x, y, true);
		int sign = target.getX() - thiz.getX() < 0 ? -1 : 1;
		this.velX = sign * 48;
		this.velY = 8 * (target.getY() - thiz.getY());
		this.y0 = y;

		setVelY(-40);
		setY(getY() + getVelY());
	}

	private int velX, velY;

	////////// NAME ////////////

	@Override
	public String getName() {
		return "yyed";
	}

	////////// TICK ////////////

	private int y0;
	private int time;
	private boolean flag;

	@Override
	public void tick() {
		super.tick();

		if (flag) {
			time++;
			reduceSpeed();

			// make the animation a little bit longer
			if (getVelX() != 0)
				super.time++;

			return;
		}

		// up then down
		if (y0 >= getY()) {
			setVelY(getVelY() + 2);
			return;
		}

		// horizontal impulsion
		setY(y0);
		flag = true;
		setVelX(velX);
		setVelY(velY);

	}

	private void reduceSpeed() {
		double reducer = Math.pow((double) time, 0.05d);

		double velX = (double) getVelX() / reducer;
		double velY = (double) getVelY() / reducer;

		setVelX((int) velX);
		setVelY((int) velY);
	}

	////////// TICK ////////////

	@Override
	public FRAME_RATE getFrameRate() {
		return FRAME_RATE.SNAIL;
	}

}
