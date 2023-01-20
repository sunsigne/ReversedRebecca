package com.sunsigne.reversedrebecca.world.mapcreator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MapCreator {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<Mappable> mappable_list = new GameLimitedList<Mappable>(LISTTYPE.ARRAY);

	public GameLimitedList<Mappable> getList() {
		return mappable_list;
	}

	////////// LEVEL CREATOR ////////////

	public void loadAllLayers(World world) {
		boolean hideRendering = false;

		for (LAYER tempLayer : LAYER.values()) {

			// load ground
			if (tempLayer.getName().contains("ground")) {
				tempLayer.addObject(new GroundRendering(world, tempLayer));
				continue;
			}

			// load content
			if (tempLayer.getName().contains("content")) {
				loadLayer(tempLayer, world.getImageMap(tempLayer));
				tempLayer.getHandler().setHideRendering(hideRendering);

				// hide content from upper layer
				if (world.getLayer(true) == tempLayer)
					hideRendering = true;
			}
		}
	}

	private void loadLayer(LAYER layer, BufferedImage image) {

		image = rescaleImage(image);
		int w = image.getWidth();
		int h = image.getHeight();
		int STEP = 1;

		for (int xx = 0; xx < h; xx += STEP) {
			for (int yy = 0; yy < w; yy += STEP) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				int x0 = xx * Size.M / STEP;
				int y0 = yy * Size.M / STEP;

				for (Mappable tempMappable : mappable_list.getList()) {

					int tempRed = tempMappable.getRedCode();
					int tempGreen = tempMappable.getGreenCode();
					int tempBlue = tempMappable.getBlueCode();

					if (red == tempRed && green == tempGreen && blue == tempBlue) {
						GameObject obj = tempMappable.createObject(x0, y0);
						layer.addObject(obj);
					}
				}
			}
		}
	}

	private static BufferedImage rescaleImage(BufferedImage image) {

		int w = image.getWidth();
		int h = image.getHeight();

		if (w == h)
			return image;

		w = w < h ? h : w;
		h = h < w ? w : h;

		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();

		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		return img;
	}

}
