package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.puzzle.dig.DigMouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigPuzzleObject;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class DigToolRenderAboveLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new DigToolRenderAboveLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private static DigMouseObject tool;

	@Override
	public void tick(Updatable object) {
		if (object instanceof DigMouseObject)
			tool = (DigMouseObject) object;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (object instanceof DigPuzzleObject)
			tool.render(g);
	}

}
