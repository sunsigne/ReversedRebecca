package com.sunsigne.reversedrebecca.object.animation;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.loot.HeartLoot;
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
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class YYedAnimationObject extends AnimationObject {

	public YYedAnimationObject(int x, int y, GoalObject target, boolean heartless) {
		super(x, y, Size.M - Size.XL / 2, Size.M - Size.XL / 2);
		GoalObject thiz = new GoalObject(x, y, true);
		int sign = target.getX() - thiz.getX() < 0 ? -1 : 1;
		this.velX = sign * 48;
		this.velY = 8 * (target.getY() - thiz.getY());
		this.y0 = y;
		this.heartless = heartless;

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
		
		if(heartless == false)
		createHeart(velX, velY);
		
		if (new PlayerFinder().getPlayer() != null)
			new PlayerFinder().getPlayer().removeHp(1);
		new CameraShaker().shaking(SHAKE.MEDIUM);
		new SoundTask().playSound(SOUNDTYPE.SOUND, "hit_medium");

	}

	private boolean heartless;
	private HeartLoot heart;

	private void reduceSpeed() {
		double reducer = Math.pow((double) time, 0.05d);
		double velX = (double) getVelX() / reducer;
		double velY = (double) getVelY() / reducer;
		setVelX((int) velX);
		setVelY((int) velY);

		if (heart == null)
			return;

		reducer = Math.pow((double) time, 0.02d);
		velX = (double) heart.getVelX() / reducer;
		velY = (double) heart.getVelY() / reducer;
		heart.setVelX((int) velX);
		heart.setVelY((int) velY);
	}

	private void createHeart(int velX, int velY) {
		GameTimer timer = new GameTimer(Game.SEC);

		heart = new HeartLoot(getX(), getY(), false) {

			@Override
			public void collidingReaction(CollisionDetector detectorObject) {
				if (timer.isReady())
					super.collidingReaction(detectorObject);
			};
		};

		heart.setVelX(velX);
		heart.setVelY(velY);

		if (new PlayerFinder().getPlayer() != null)
			Handler.getHandler(new PlayerFinder().getPlayer()).addObject(heart);
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
		new GameTimer(4 * Game.SEC, () -> {
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
