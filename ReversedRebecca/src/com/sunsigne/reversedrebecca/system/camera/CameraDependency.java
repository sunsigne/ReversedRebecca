package com.sunsigne.reversedrebecca.system.camera;

import java.awt.Graphics;
import java.awt.Graphics2D;

public interface CameraDependency {

	////////// CAMERA ////////////
	
	final Camera CAMERA = new Camera();
	
	boolean isCameraDependant();
	
	////////// RENDER ////////////
	
	default void renderDependency(Graphics g, boolean active) {
		if (!isCameraDependant())
			return;

		Graphics2D g2d = (Graphics2D) g;
		int value = active ? 1 : -1;
		g2d.translate(value * CAMERA.getX(), value * CAMERA.getY());
	}
}
