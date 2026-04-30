package com.sunsigne.reversedrebecca.object.animation;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class YYedAnimationObject extends AnimationObject {

	public YYedAnimationObject(int x, int y, GoalObject target) {
		super(x, y, Size.M - Size.XL / 2, Size.M - Size.XL / 2);
		GoalObject thiz = new GoalObject(x, y, true);
		int sign = target.getX() - thiz.getX() < 0 ? -1 : 1;
		this.velX = sign * 48;
		this.velY = 8 * (target.getY() - thiz.getY());
		this.y0 = y;

		setVelY(-30);
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
		if (new PlayerFinder().getPlayer() != null)
			new PlayerFinder().getPlayer().removeHp(1);
		new CameraShaker().shaking(SHAKE.MEDIUM);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "hit_medium");

	}

	private void reduceSpeed() {
		double reducer = Math.pow((double) time, 0.05d);

		double velX = (double) getVelX() / reducer;
		double velY = (double) getVelY() / reducer;

		setVelX((int) velX);
		setVelY((int) velY);
	}

	@Override
	protected void actionOnDisappearing() {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		player.setCondition(CONDITION.KO_UPSIDEDOWN);
		player.setX(getX());
		player.setY(getY());
		player.setDisplayXY(0, 0);
		new GameTimer(3 * Game.SEC, () -> {
			player.setCondition(CONDITION.GOOD);
			player.setStunned(false);
		});
	}

	////////// TICK ////////////

	@Override
	public FRAME_RATE getFrameRate() {
		return FRAME_RATE.SNAIL;
	}

}
