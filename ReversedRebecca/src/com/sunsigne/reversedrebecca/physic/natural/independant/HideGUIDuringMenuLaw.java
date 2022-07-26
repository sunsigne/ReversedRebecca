package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.gui.GUI;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Health;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class HideGUIDuringMenuLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new HideGUIDuringMenuLaw();

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
		if (object instanceof GUI == false)
			return;
		
		boolean menu = LAYER.MENU.getHandler().getList().isEmpty() == false;
		if (menu)
			setObjectVisible(g, object, false);
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (object instanceof GUI == false)
			return;
		
		setObjectVisible(g, object, true);
	}

	private void setObjectVisible(Graphics g, Updatable object, boolean visible) {
		float alpha = visible ? 1 : 0;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

}
