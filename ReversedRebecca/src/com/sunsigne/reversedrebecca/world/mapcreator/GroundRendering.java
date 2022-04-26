package com.sunsigne.reversedrebecca.world.mapcreator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class GroundRendering implements Updatable {

	public GroundRendering(World world, LAYER layer) {
		this.world = world;
		this.layer = layer;
	}

	////////// TICK ////////////
	
	@Override
	public void tick() {
	
	}

	////////// RENDER ////////////

	private World world;
	private LAYER layer;
	
	@Override
	public void render(Graphics g) {

		BufferedImage img = world.getImageMap(layer);
		
		int pixel = 16;
		int width = img.getWidth() * Size.M / pixel;
		int height = img.getHeight() * Size.M / pixel;

		// draw gray lawer UNDER current ground (for up layers)
		new TransluantLayer().drawGray(g, width, height);
		g.drawImage(img, 0, 0, width, height, null);
	}
	
}
