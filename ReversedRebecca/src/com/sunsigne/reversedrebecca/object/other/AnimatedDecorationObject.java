package com.sunsigne.reversedrebecca.object.other;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.ArrayCombiner;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.Animation;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;

public class AnimatedDecorationObject extends DecorationObject implements SheetableImage {

	public AnimatedDecorationObject(int x, int y, int w, int h, String name, int animation_time, boolean cycle) {
		super(x, y, w, h, name);
		this.animation_time = time = animation_time;
		loadAnimation(cycle);
	}

	////////// NAME ////////////

	@Override
	protected String getPath() {
		return "textures/other/animation/";
	}

	@Override
	public String toString() {
		var clazz = "ANIMATION";
		return clazz + " : " + getName().toUpperCase();
	}

	////////// TICK ////////////

	private int animation_time;
	private int time;

	@Override
	public void tick() {
		runAnimation();
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = animation_time;
			animation.cycle();
		}
	}

	////////// TEXTURE ////////////

	protected Cycloid<BufferedImage> animation;

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetWidth() {
		return getWidth() / 6;
	}

	@Override
	public int getSheetHeight() {
		return getHeight() / 6;
	}

	protected int getNumberOfTimesFirstImageIsRepeated() {
		return 0;
	}

	private void loadAnimation(boolean cycle) {
		BufferedImage sheet = new ImageTask().loadImage(getPath() + getName());
		Animation animationImages = new Animation(sheet, getSheetWidth(), getSheetHeight());

		BufferedImage firstImage = getSheetSubImage(sheet, 1);
		GameList<BufferedImage> list = new GameList<>(LISTTYPE.ARRAY);

		for (int repeat = 0; repeat < getNumberOfTimesFirstImageIsRepeated(); repeat++)
			list.addObject(new ImageTask().drawCopyOf(firstImage));

		BufferedImage[] firstImages = new BufferedImage[list.getList().size()];
		firstImages = list.getList().toArray(firstImages);

		BufferedImage images[] = new ArrayCombiner<BufferedImage>().combine(BufferedImage.class, firstImages,
				animationImages.getImages());

		if (cycle)
			animation = new Cycloid<BufferedImage>(images);
		else
			animation = new LimitedCycloid<BufferedImage>(images);
	}

	@Override
	public BufferedImage getImage() {
		return animation.getState();
	}

}
