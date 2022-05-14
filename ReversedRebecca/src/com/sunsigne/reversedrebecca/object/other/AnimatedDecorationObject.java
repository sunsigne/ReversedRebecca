package com.sunsigne.reversedrebecca.object.other;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class AnimatedDecorationObject extends DecorationObject {

	public AnimatedDecorationObject(int x, int y, String name, int animation_time, int frames, boolean cycle) {
		this(x, y, Size.M, Size.M, name, animation_time, frames, cycle);
	}

	public AnimatedDecorationObject(int x, int y, int w, int h, String name, int animation_time, int frames,
			boolean cycle) {
		super(x, y, w, h, name);
		this.animation_time = animation_time;
		this.time = animation_time;
		this.frames = frames;
		loadAnimation(cycle);
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
	private int frames;

	private void loadAnimation(boolean cycle) {

		String path = "textures/other/decoration/" + getName() + "_";
		ImageTask loader = new ImageTask();

		GameList<BufferedImage> list = new GameList<>(LISTTYPE.LINKED);

		for (int index = 0; index < frames; index++) {
			// put a "0" in front of each number below 10
			String formated_index = index < 10 ? "0" + index : String.valueOf(index);
			list.addObject(loader.loadImage(path + formated_index));
		}

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
