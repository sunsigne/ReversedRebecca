package com.sunsigne.reversedrebecca.world.updatable;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

		for (LAYER tempLayer : LAYER.values()) {
			if (!tempLayer.getHandler().isCameraDependant())
				return;

			if (tempLayer.getName().contains("content"))
				continue;
			
			ImageMap imageMap = getExtraBehaviorsWorld().getImageMap(tempLayer);
			BufferedImage img = imageMap.getImage();

			int pixel = 16;
			int width = img.getWidth() * Size.M / pixel;
			int height = img.getHeight() * Size.M / pixel;

			drawTransluantLayer(g, width, height);
			g.drawImage(img, 0, 0, width, height, null);

			LAYER world_layer = getExtraBehaviorsWorld().getLayer(false);
			
			if (tempLayer == world_layer)
				return;
		}
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
