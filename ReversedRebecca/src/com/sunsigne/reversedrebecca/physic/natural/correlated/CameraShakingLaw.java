package com.sunsigne.reversedrebecca.physic.natural.correlated;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class CameraShakingLaw implements PhysicLaw {

	////////// TICK ////////////

	private int x, y;
	protected int shift;

	@Override
	public void tick(Updatable object) {
		if (shouldRefreshShift(object) == false)
			return;

		if (shift == 0)
			return;

		x = new RandomGenerator().getIntBetween(-shift, shift);
		y = new RandomGenerator().getIntBetween(-shift, shift);

		if (shift > 0)
			shift--;
	}

	private boolean shouldRefreshShift(Updatable object) {
		if (object instanceof World)
			return true;
		if (object instanceof Puzzle)
			return true;
		if (object instanceof ChatBox)
			return true;

		return false;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		if (shift != 0)
			renderDependency(g, true);
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (shift != 0)
			renderDependency(g, false);
	}

	private void renderDependency(Graphics g, boolean active) {
		Graphics2D g2d = (Graphics2D) g;
		int value = active ? 1 : -1;
		g2d.translate(value * x, value * y);
	}

}
