package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class FadeMenuLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new FadeMenuLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private boolean fading;

	public void setFading(boolean fading) {
		this.fading = fading;

		if (!fading) {
			alpha = 0;
			LAYER.MENU.getHandler().clear();
		}
	}

	public boolean isFading() {
		return fading;
	}

	@Override
	public void tick(Updatable object) {
		if (object instanceof World == false)
			return;

		if (!fading) {
			alpha = 1f;
			return;
		}

		alpha = alpha - 0.015f;

		if (alpha < 0)
			setFading(false);
	}

	////////// RENDER ////////////

	private float alpha = 1f;

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		if (!fading)
			return;

		if (object == null)
			return;

		if (object.getHandler() != LAYER.MENU.getHandler())
			return;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (!fading && alpha != 0)
			return;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
