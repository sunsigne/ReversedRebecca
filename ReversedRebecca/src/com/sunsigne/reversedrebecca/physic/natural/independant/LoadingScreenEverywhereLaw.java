package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LoadingScreenEverywhereLaw extends IndependantLaw implements CameraDependency {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new LoadingScreenEverywhereLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		if (LAYER.LOADING.getHandler().getList().isEmpty())
			return;

		if (object instanceof LoadingScreen)
			return;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
