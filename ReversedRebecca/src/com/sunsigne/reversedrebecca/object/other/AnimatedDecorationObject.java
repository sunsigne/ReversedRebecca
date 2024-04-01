package com.sunsigne.reversedrebecca.object.other;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class AnimatedDecorationObject extends DecorationObject {

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

	protected int getNumberOfTimesFirstImageIsRepeated() {
		return 0;
	}

	private void loadAnimation(boolean cycle) {

		String path = getPath() + getName() + "_";
		ImageTask loader = new ImageTask();

		GameList<BufferedImage> list = new GameList<>(LISTTYPE.ARRAY);
		int index = 0;
		BufferedImage image = null;

		do {
			String formated_index = new FormattedString().getNumber(index);
			image = loader.loadImage(path + formated_index, true);

			if (image == null)
				break;

			list.addObject(loader.loadImage(path + formated_index));

			if (index == 0)
				for (int repeat = 0; repeat < getNumberOfTimesFirstImageIsRepeated(); repeat++)
					list.addObject(loader.loadImage(path + formated_index));

			index++;
		} while (true);

		// converting the list into an array
		BufferedImage[] images = new BufferedImage[list.getList().size()];
		images = list.getList().toArray(images);

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
