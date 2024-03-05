package com.sunsigne.reversedrebecca.object.piranha.living.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;

public class LivingAnimation implements SheetableImage {

	public LivingAnimation(LivingObject living, int animation_time, boolean orientable, int... col) {
		this.col = col;
		this.living = living;
		this.animation_time = animation_time; // -1 for no animation
		this.orientable = orientable;
		loadAnimations();
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	////////// TICK ////////////

	private int animation_time;
	private int time = animation_time;

	@Override
	public void tick() {
		run();
	}

	public void run() {
		// no animation case
		if (animation_time == -1)
			return;

		time--;
		if (time < 0) {
			time = animation_time;
			for (int i = 0; i < cycloid.length; i++) {
				cycloid[i].cycle();
			}
		}
	}

	public void freeze() {
		// no animation case
		if (animation_time == -1)
			return;

		for (int i = 0; i < cycloid.length; i++) {
			cycloid[i].setState(0);
		}
		time = animation_time;
	}

	////////// TEXTURE ////////////

	private int[] col;

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 5; // no orientable case
	}

	private boolean orientable;

	@SuppressWarnings("unchecked")
	private Cycloid<BufferedImage>[] cycloid = new Cycloid[4];

	private static HashMap<BufferedImage, BufferedImage> map = new HashMap<>();

	private void loadAnimations() {
		cycloid[DIRECTION.LEFT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.LEFT));
		cycloid[DIRECTION.RIGHT.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.RIGHT));
		cycloid[DIRECTION.UP.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.UP));
		cycloid[DIRECTION.DOWN.getNum()] = new Cycloid<BufferedImage>(loadAnimation(DIRECTION.DOWN));
	}

	private BufferedImage[] loadAnimation(DIRECTION direction) {
		int row = orientable ? 1 + direction.getNum() : getSheetRowCriterion();

		BufferedImage[] animation = new BufferedImage[col.length];
		BufferedImage highlight = null;
		int index = 0;

		do {
			animation[index] = getSheetSubImage(loadImage(), col[index], row, getSheetWidth(), getSheetHeight());
			highlight = getSheetSubImage(loadHighlightImage(), col[index], row, getSheetWidth() + 2, getSheetHeight() + 2);
			map.put(animation[index], highlight);
			index++;
		} while (index < col.length);

		return animation;
	}

	private BufferedImage living_img;
	protected BufferedImage highlight_image;

	private BufferedImage loadImage() {
		if (living_img != null)
			return living_img;

		String path = "textures/characters/" + living.getName() + "/" + living.getName();
		living_img = new ImageTask().loadImage(path, true);

		// load error character instead of missing texture
		if (living_img == null)
			living_img = new ImageTask().loadImage(path.replace(living.getName(), "error"));

		return living_img;
	}

	private BufferedImage loadHighlightImage() {
		if (highlight_image != null)
			return highlight_image;

		String path = "textures/characters/" + living.getName() + "/" + living.getName() + "_highlight";
		highlight_image = new ImageTask().loadImage(path, true);

		// load error character instead of missing texture
		if (highlight_image == null)
			highlight_image = new ImageTask().loadImage(path.replace(living.getName(), "error"));

		return highlight_image;
	}
	
	////////// RENDER ////////////

	public BufferedImage getImage() {
		int facing = living.getFacing().getNum();
		return cycloid[facing].getState();
	}

	public BufferedImage getHighlightFromImage(BufferedImage image) {
		return map.get(image);
	}

	@Override
	public void render(Graphics g) {

	}

}