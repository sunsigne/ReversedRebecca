package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.VirusObject;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class VirusRenderAboveLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new VirusRenderAboveLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private static VirusObject virus;

	@Override
	public void tick(Updatable object) {
		if (object instanceof VirusObject)
			virus = (VirusObject) object;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (object instanceof ProcessorObject)
			virus.render(g);
	}

}
