package com.sunsigne.reversedrebecca.object.hostile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.ExplodeRubbleAnimationObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;

public class RollingBomb extends GameObject implements CollisionReactor {

	public RollingBomb(int x, int y) {
		super(x, y, Size.XL / 2, Size.XL / 2);
	}

	////////// MOUVEMENT ////////////

	private final int SPEED = 2;

	public void movingtoPlayer() {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		float diffX = getX() - player.getX();
		float diffY = getY() - player.getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) / 6;

		setVelX(SPEED * Math.round((-1 / distance) * diffX));
		setVelY(SPEED * Math.round((-1 / distance) * diffY));
	}

	////////// TICK ////////////

	private final int EXPLODING_TIME = 35;
	private int time;

	@Override
	public void tick() {
		time++;

		if (time >= EXPLODING_TIME)
			explode();
	}

	private void explode() {
		new SoundTask().playSound(SOUNDTYPE.SOUND, "explosion_small");

		var handler = getHandler();
		handler.removeObject(this);
		handler.addObject(new ExplodeRubbleAnimationObject(getX(), getY()));
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

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof Player == false)
			return;

		collidingReaction(detectorObject, false, () -> {
			new PlayerFinder().getPlayer().removeHp();
			explode();
		});
	}

}
