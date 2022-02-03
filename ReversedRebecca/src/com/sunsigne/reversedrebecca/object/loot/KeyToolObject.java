package com.sunsigne.reversedrebecca.object.loot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.ressources.DIFFICULTY;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class KeyToolObject extends LootObject {

	public KeyToolObject(DIFFICULTY difficulty, int x, int y) {
		super(x, y);
		loadImages();
		this.difficulty = difficulty;
	}

	private DIFFICULTY difficulty;
	
	public DIFFICULTY getDifficulty() {
		return difficulty;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////
	
	private BufferedImage img;

	private void loadImages() {
		img = new ImageTask().loadImage("textures/" + "gui_heart_empty" + ".png");
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> {
			getHandler().removeObject(this);
		});
	}

}
