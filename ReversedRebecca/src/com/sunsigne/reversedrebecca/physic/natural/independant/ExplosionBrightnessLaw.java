package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.animation.ExplosionAnimationObject;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.mapcreator.LightRendering;

public class ExplosionBrightnessLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new ExplosionBrightnessLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private boolean fading;
	private boolean brightning;

	public void setFading(boolean fading) {
		this.fading = fading;
		brightning = false;

		if (!fading)
			alpha = 1f;
	}

	@Override
	public void tick(Updatable object) {
		if (object instanceof ExplosionAnimationObject)
			if (brightning == false && fading == false) {
				brightning = true;
				alpha = 1f;
			}

		if (object instanceof World == false)
			return;

		if (brightning) {
			alpha = alpha - 0.15f;
			if (alpha < 0f)
				setFading(true);
		}

		if (fading)
			alpha = alpha + 0.015f;

		if (alpha > 1f)
			setFading(false);
	}

	////////// RENDER ////////////

	private float alpha = 1f;

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		if (object instanceof LightRendering == false)
			return;

		// Graphics2D g2d = (Graphics2D) g;
		// g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0, alpha)));
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (object instanceof LightRendering == false)
			return;

		// Graphics2D g2d = (Graphics2D) g;
		// g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
