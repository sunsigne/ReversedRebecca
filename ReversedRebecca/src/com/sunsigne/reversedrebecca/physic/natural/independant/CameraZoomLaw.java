package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.camera.Camera;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class CameraZoomLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new CameraZoomLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	private boolean active;
	private boolean zooming;

	public void setActive(boolean active) {
		if (this.active)
			lens = MAX_ZOOM;
		else
			lens = 1f;

		this.active = active;
		this.zooming = active;
	}

	@Override
	public void tick(Updatable object) {
		if (object instanceof World == false)
			return;

		new Camera().setZoom(1f);
		if (active == false)
			return;

		new Camera().setZoom(Math.min(lens, MAX_ZOOM));

		if (zooming)
			lens = lens + GROTHW;
		else
			lens = lens - GROTHW;

		if (lens >= MAX_ZOOM * 2.5f)
			zooming = false;
		if (lens <= 1f)
			setActive(false);
	}

	////////// RENDER ////////////

	private float lens;
	private float GROTHW = 0.15f;
	private float MAX_ZOOM = 3f;

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
