package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Health;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class BlinkingRecoveringLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new BlinkingRecoveringLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick(Updatable object) {
		if (object instanceof World == false) // to happens ONCE a tick
			return;

		time--;

		if (time <= 0)
			time = Game.SEC - 1;
	}

	public boolean shouldBlink() {
		return time % 4 == 0;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		setObjectVisible(g, object, true);
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		setObjectVisible(g, object, false);
	}

	private void setObjectVisible(Graphics g, Updatable object, boolean shouldBlink) {
		if (object instanceof Health == false)
			return;

		Health health = (Health) object;

		if (!health.isRecovering())
			return;

		float alpha = shouldBlink & shouldBlink() ? 0 : 1;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

}
