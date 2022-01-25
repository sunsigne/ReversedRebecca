package com.sunsigne.reversedrebecca.object.puzzler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class DoorObject extends GameObject implements CollisionReactor {

	// change lvl into enum DIFFICULTY
	public DoorObject(int lvl, int x, int y) {
		super(x, y);
		this.lvl = lvl;
		loadImages();
	}
	
	////////// NAME ////////////

	public String getName() {
		return "door";
	}
	
	////////// LEVEL ////////////

	private int lvl;

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	// Create a list instead of an array
	private BufferedImage[] images = new BufferedImage[7];
	
	// make a loop, taking the name (color) from a getName into the enum DIFFICULTY
	private void loadImages() {
		images[0] = new ImageTask().loadImage("textures/" + getName() + "/" + "none" + ".png");
		images[1] = new ImageTask().loadImage("textures/" + getName() + "/" + "cyan" + ".png");
		images[2] = new ImageTask().loadImage("textures/" + getName() + "/" + "green" + ".png");
		images[3] = new ImageTask().loadImage("textures/" + getName() + "/" + "yellow" + ".png");
		images[4] = new ImageTask().loadImage("textures/" + getName() + "/" + "orange" + ".png");
		images[5] = new ImageTask().loadImage("textures/" + getName() + "/" + "red" + ".png");
		images[6] = new ImageTask().loadImage("textures/" + getName() + "/" + "purple" + ".png");
	}
	
	public BufferedImage getImage() {
		return images[lvl];
	}

	////////// RENDER ////////////

	// this method is the only one which is ok XD but it should be in a parent PuzzleObject
	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////
	
	// of course the final door mecanics is more complexe
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> getHandler().removeObject(this));
	}

}
