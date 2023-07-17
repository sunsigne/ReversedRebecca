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

public class FallingBomb extends GameObject implements CollisionReactor {

	public FallingBomb(int x, int y) {
		super(x, y, Size.XL / 2, Size.XL / 2);
	}

	////////// TICK ////////////

	private final int FALLING_TIME = 25;
	private int time;

	@Override
	public void tick() {
		time++;

		if (time == FALLING_TIME)
			canHurtPlayer = true;

		if (time > FALLING_TIME)
			explode();
	}

	private void explode() {
		new SoundTask().playSound(SOUNDTYPE.SOUND, "explosion_medium");

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
		int size = 3 * (FALLING_TIME - time);
		
		int x = getX() - size / 2;
		int y = getY() - size / 2;
		int w = getWidth() + size;
		int h = getHeight() + size;
		
		g.drawImage(getImage(), x, y, w, h, null);
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

	private boolean canHurtPlayer;

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof Player == false)
			return;

		collidingReaction(detectorObject, false, () -> {
			if (canHurtPlayer)
				new PlayerFinder().getPlayer().removeHp();
		});
	}

}
