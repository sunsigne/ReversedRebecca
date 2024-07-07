package com.sunsigne.reversedrebecca.object.hostile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.animation.ExplosionAnimationObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class ExplodingBomb extends GameObject implements CollisionReactor {

	public ExplodingBomb(int x, int y) {
		super(x, y, Size.XL / 2, Size.XL / 2);
	}

	public ExplodingBomb(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	////////// MOUVEMENT ////////////

	public void movingtoPlayer() {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		float diffX = getX() - player.getX();
		float diffY = getY() - player.getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) / 6;

		setVelX(getSpeed() * Math.round((-1 / distance) * diffX));
		setVelY(getSpeed() * Math.round((-1 / distance) * diffY));
	}

	////////// HP ////////////

	protected void removeHp() {
		Player player = new PlayerFinder().getPlayer();
		if (player != null)
			player.removeHp();
	}

	////////// TICK ////////////

	public abstract int getExplodingTime();

	private int time;

	@Override
	public void tick() {
		time++;

		if (time >= getExplodingTime())
			explode();
	}

	protected void explode() {
		var handler = getHandler();
		if (handler == null)
			return;

		new SoundTask().playSound(SOUNDTYPE.SOUND, "explosion_small");
		handler.removeObject(this);
		handler.addObject(new ExplosionAnimationObject(getX(), getY()));
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/tools/bomb_null");
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

}
