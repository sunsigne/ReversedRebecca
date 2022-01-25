package com.sunsigne.reversedrebecca.world.mapcreator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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

		drawTransluantLayer(g, width, height);
		g.drawImage(img, 0, 0, width, height, null);
	}
	
	private void drawTransluantLayer(Graphics g, int width, int height) {
		Graphics2D g2d = (Graphics2D) g;
		Color color = new Color(64, 64, 64);
		g2d.setColor(color);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		g2d.fillRect(0, 0, width, height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}


}
