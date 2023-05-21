package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.MouseObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MouseObjectRenderAboveLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new MouseObjectRenderAboveLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private static MouseObject mouse;

	@Override
	public void tick(Updatable object) {
		if (object instanceof Player)
			mouse = null;

		if (object instanceof MouseObject)
			if (((MouseObject) object).replaceMouse())
				mouse = (MouseObject) object;

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (object instanceof PuzzleObject)
			if (mouse != null)
				mouse.render(g);
	}

}
