package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.animation.YYedAnimationObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Pusher.PUSHING_DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.YY;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.finder.SightFinder;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.Camera;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DoubleYSSJ2UppercutPattern extends BossPattern {

	protected DoubleYSSJ2UppercutPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}

	public DoubleYSSJ2UppercutPattern(BossObject boss) {
		this(boss, 24, 7 * Game.SEC);
	}

	////////// PATTERN ////////////

	public DoubleYBoss getBoss() {
		return (DoubleYBoss) super.getBoss();
	}

	////////// TICK ////////////

	private int time = 5 * Game.SEC;

	@Override
	public void tick() {
		super.tick();

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		time++;
		if (time < getDelayBetweenTwoAttacks())
			return;

		time = 0;
		attack(player);
	}

	protected void attack(Player player) {

		int camX = Window.WIDHT / 2 - (int) new Camera().getX();
		int camY = Window.HEIGHT / 2 - (int) new Camera().getY();
		GoalObject center = new GoalObject(camX, camY, true);

		// tp boss
		tpBoss(player);

		// boss facing center
		GoalObject sight = new GoalObject(center.getX(), center.getY(), false);
		SightFinder sightFinder = new SightFinder(getBoss(), sight);
		DIRECTION facing = sightFinder.getDirectionOfGoalFromObserver();
		getBoss().setFacing(facing);

		// boss punching
		getBoss().setPushingDirection(PUSHING_DIRECTION.FACING_OF_PUSHER);
		getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.UPPERCUT);

		// uppercut player
		uppercutPlayer(player);
		Handler.getHandler(player).addObject(new YYedAnimationObject(player.getX(), player.getY(), center));

	}

	private void tpBoss(Player player) {
		new SoundTask().playSound(SOUNDTYPE.SOUND, "dbz_tp");
		Handler.getHandler(player).addObject(getAfterImage());
		getBoss().setX(player.getX());
		getBoss().setY(player.getY());
	}

	public YY getAfterImage() {
		YY yy = new YY(getBoss().getX(), getBoss().getY()) {

			////////// NAME ////////////

			@Override
			public String getTextureName() {
				return super.getTextureName() + "_ssj2";
			}

			////////// SIZE ////////////

			@Override
			public int getHeight() {
				return 8 * getPixelSize();
			}

			////////// DOUBLE Y CONDITION ////////////

			@Override
			public DOUBLE_Y_CONDITION getDoubleYCondition() {
				return DOUBLE_Y_CONDITION.DBZ_TP;
			}

			////////// TICK ////////////

			private final int ANIMATION_TIME = 16;
			private int time = ANIMATION_TIME;

			@Override
			public void tick() {
				animation.run();
				time--;
				if (time <= 0)
					removeObject();
			}

		};

		yy.setDisplayXY(0, -8);
		return yy;
	}

	private void uppercutPlayer(Player player) {
		new SoundTask().playSound(SOUNDTYPE.SOUND, "hit_medium");
		player.setDisplayXY(2 * Window.WIDHT, 2 * Window.HEIGHT);
		player.setCondition(CONDITION.KO_UPSIDEDOWN);
		player.setStunned(true);
		player.removeHp(1);
		new GameTimer(80, () -> new SoundTask().playSound(SOUNDTYPE.SOUND, "whip"));
	}

}
