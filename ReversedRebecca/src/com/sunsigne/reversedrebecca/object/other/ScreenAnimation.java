package com.sunsigne.reversedrebecca.object.other;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.system.Window;

public class ScreenAnimation extends AnimatedDecorationObject {

	public ScreenAnimation(String name, int animation_time, int iterations, boolean transition) {
		super(0, 0, Window.WIDHT, Window.HEIGHT, name, animation_time, true);
		this.iterations = iterations;
		this.transition = transition;
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

		if (iterations <= 0) {
			fading = true;
			if (transition == false)
				removeObject();
			else if (alpha <= 0f)
				removeObject();
		}
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

	////////// RENDER ////////////

	private boolean transition;
	private boolean fading;
	private float alpha;
	private float ALPHA_GROWTH = 0.15f;

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (transition) {
			if (fading)
				alpha = Math.max(alpha - ALPHA_GROWTH, 0);
			else
				alpha = Math.min(alpha + ALPHA_GROWTH, 1f);

			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		}

		super.render(g);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
