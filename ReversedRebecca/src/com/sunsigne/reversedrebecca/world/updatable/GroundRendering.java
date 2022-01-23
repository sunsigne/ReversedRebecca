package com.sunsigne.reversedrebecca.world.updatable;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.ImageMap;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.behaviors.WorldRenderBehavior;

public class GroundRendering implements WorldRenderBehavior {

	public GroundRendering(World world) {
		this.world = world;
	}

	////////// BEHAVIOR ////////////

	private World world;

	@Override
	public World getExtraBehaviorsWorld() {
		return world;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		
		int pixel = 16;
		LAYER layer = getExtraBehaviorsWorld().getLayer(false);
		ImageMap imageMap = getExtraBehaviorsWorld().getImageMap(layer);
		BufferedImage img = imageMap.getImage();

		g.drawImage(img, 0, 0, img.getWidth() * Size.M / pixel, img.getHeight() * Size.M / pixel, null);
	}

}
