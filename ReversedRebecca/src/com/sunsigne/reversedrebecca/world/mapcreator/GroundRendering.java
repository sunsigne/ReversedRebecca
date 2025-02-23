package com.sunsigne.reversedrebecca.world.mapcreator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class GroundRendering implements Updatable, TickFree, CameraDependency {

	public GroundRendering(World world, LAYER layer) {
		this.world = world;
		this.layer = layer;
	}

	private World world;

	public World getWorld() {
		return world;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.SHAKER;
	}

	////////// RENDER ////////////

	private LAYER layer;

	@Override
	public void render(Graphics g) {
		if (world.getLayer(false) != layer)
			return;

		BufferedImage img = world.getImageMap(layer);

		int pixel = 16;
		int ratio = Size.M / pixel;
		int width = img.getWidth() * ratio;
		int height = img.getHeight() * ratio;

		// draw gray lawer UNDER current ground (for up layers)
		new TransluantLayer().drawGray(g, width, height);

		// BufferedImage optimized_img = getOptimizedImage(img, ratio);
		BufferedImage optimized_img = img;
		g.drawImage(optimized_img, 0, 0, width, height, null);
	}

	@SuppressWarnings("unused")
	private BufferedImage getOptimizedImage(BufferedImage img, int ratio) {
		BufferedImage optimized_img = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = optimized_img.createGraphics();

		int x = -(int) CAMERA.getX() / ratio;
		int y = -(int) CAMERA.getY() / ratio;

		g2d.setClip(x - 25, y - 25, 350, 215);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();

		return optimized_img;
	}

}
