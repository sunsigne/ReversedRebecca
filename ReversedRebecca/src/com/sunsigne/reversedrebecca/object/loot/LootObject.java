package com.sunsigne.reversedrebecca.object.loot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;

public abstract class LootObject extends GameObject implements CollisionReactor {

	public LootObject(int x, int y) {
		super(x, y);
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	public abstract BufferedImage getImage();

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (!(detectorObject instanceof Player))
			return;

		collidingReaction(detectorObject, false, () -> {
			getHandler().removeObject(this);
			actionWhenLooted();
		});
	}

	public abstract void actionWhenLooted();

}
