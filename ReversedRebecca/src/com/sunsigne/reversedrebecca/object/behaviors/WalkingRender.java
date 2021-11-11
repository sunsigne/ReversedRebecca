package com.sunsigne.reversedrebecca.object.behaviors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class WalkingRender implements Behavior {

	public WalkingRender(ExtraBehaviorsObject object) {
		this.object = object;
	}

	////////// BEHAVIOR ////////////

	private ExtraBehaviorsObject object;

	@Override
	public ExtraBehaviorsObject getExtraBehaviorsObject() {
		return object;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private Cycloid<BufferedImage> walking_left = new Cycloid(getImages(DIRECTION.LEFT));
	private Cycloid<BufferedImage> walking_right = new Cycloid(getImages(DIRECTION.RIGHT));
	private Cycloid<BufferedImage> walking_up = new Cycloid(getImages(DIRECTION.UP));
	private Cycloid<BufferedImage> walking_down = new Cycloid(getImages(DIRECTION.DOWN));
	private BufferedImage walking_ground = getImage("ground");

	private BufferedImage getImage(String imageName) {
		return new ImageTask().loadImage("textures/characters/" + object.getName() + "/walking_" + imageName);
	}
	
	private BufferedImage[] getImages(DIRECTION direction) {
		
		BufferedImage img0 = getImage(direction.getName() + "_1");
		BufferedImage img1 = getImage(direction.getName() + "_0");
		BufferedImage img2 = getImage(direction.getName() + "_1");
		BufferedImage img3 = getImage(direction.getName() + "_2");
		
		return new BufferedImage[] {img0, img1, img2, img3};
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}