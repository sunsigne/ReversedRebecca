package com.sunsigne.reversedrebecca.object.other;

import com.sunsigne.reversedrebecca.system.Window;

public class ScreenAnimation extends AnimatedDecorationObject {

	public ScreenAnimation(String name, int animation_time, int iterations) {
		super(0, 0, Window.WIDHT, Window.HEIGHT, name, animation_time, true);
		this.iterations = iterations;
	}

	////////// NAME ////////////

	@Override
	protected String getPath() {
		return "textures/other/screen/";
	}

	@Override
	public String toString() {
		var clazz = "SCREEN ANIMATION";
		return clazz + " : " + getName().toUpperCase();
	}

	////////// TICK ////////////

	private int iterations;

	@Override
	public void tick() {
		super.tick();

		if (getTime() != 0)
			return;

		iterations--;
		if (iterations <= 0)
			removeObject();
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetWidth() {
		return Window.WIDHT;
	}

	@Override
	public int getSheetHeight() {
		return Window.HEIGHT;
	}

}
