package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.puzzle.dig.BuriedObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigMouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DigToolObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.DirtObject;
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
		if (object instanceof DigToolObject || object instanceof DirtObject || object instanceof BuriedObject)
			tool.render(g);
	}

}
