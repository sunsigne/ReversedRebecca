package com.sunsigne.reversedrebecca.object.hostile;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzler.animation.ExplodeRubbleAnimationObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class BigRollingBomb extends RollingBomb implements CollisionReactor {

	public BigRollingBomb(int x, int y) {
		super(x, y, Size.XL, Size.XL);
	}

	////////// MOUVEMENT ////////////

	@Override
	public int getSpeed() {
		return 1;
	}

	////////// TICK ////////////

	public int getExplodingTime() {
		return 50;
	}

	@Override
	protected void explode() {
		var handler = getHandler();
		if (handler == null)
			return;
		
		new SoundTask().playSound(SOUNDTYPE.SOUND, "explosion_large");
		handler.removeObject(this);
		handler.addObject(new ExplodeRubbleAnimationObject(getX(), getY(), Size.XL, Size.XL));

		createSubBombs(handler);
	}

	private void createSubBombs(Handler handler) {
		RollingBomb[] bombs = new RollingBomb[4];
		for (int index = 0; index < 4; index++)
			bombs[index] = new RollingBomb(getX(), getY());

		int speed = 5;
		bombs[0].setVelX(speed);
		bombs[0].setVelY(speed);
		bombs[1].setVelX(speed);
		bombs[1].setVelY(-speed);
		bombs[2].setVelX(-speed);
		bombs[2].setVelY(speed);
		bombs[3].setVelX(-speed);
		bombs[3].setVelY(-speed);

		for (int index = 0; index < 4; index++)
			handler.addObject(bombs[index]);
	}

	////////// COLLISION ////////////

	@Override
	public Rectangle getBounds() {
		int shrink = Size.XS / 2;		
		int x = getX() + shrink;
		int y = getY() + shrink;
		int w = getWidth() - 2 * shrink;
		int h = getHeight() - 2 * shrink;
		return new Rectangle(x, y, w, h);
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof Player == false)
			return;

		collidingReaction(detectorObject, false, () -> {
			new PlayerFinder().getPlayer().removeHp(2);
			explode();
		});
	}

}
