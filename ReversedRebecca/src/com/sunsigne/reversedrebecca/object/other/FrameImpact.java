package com.sunsigne.reversedrebecca.object.other;

import com.sunsigne.reversedrebecca.system.Window;

public class FrameImpact extends DecorationObject {

	public FrameImpact(String name) {
		super(0, 0, Window.WIDHT, Window.HEIGHT, name);
	}

	////////// NAME ////////////

	@Override
	protected String getPath() {
		return "textures/other/frameimpact/";
	}

	@Override
	public String toString() {
		var clazz = "FRAME IMPACT";
		return clazz + " : " + getName().toUpperCase();
	}

	////////// TICK ////////////

	private final int FRAME_TIME = 8;
	private int time = FRAME_TIME;

	@Override
	public void tick() {
		time--;
		if (time < 0)
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
