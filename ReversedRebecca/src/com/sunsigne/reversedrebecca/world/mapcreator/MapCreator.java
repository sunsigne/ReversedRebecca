package com.sunsigne.reversedrebecca.world.mapcreator;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.ImageMap;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MapCreator {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<Mappable> mappable_list = new GameLimitedList<Mappable>(LISTTYPE.ARRAY);

	public GameLimitedList<Mappable> getList() {
		return mappable_list;
	}

	////////// LEVEL CREATOR ////////////

	public void loadLevel(ImageMap gameMap) {
		LAYER layer = gameMap.getLayer();
		BufferedImage image = gameMap.getImage();

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
		Game.getInstance().forceLoop();
	}

}
