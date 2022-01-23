package com.sunsigne.reversedrebecca.world;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class LevelData {
	
	public LevelData(String level_name) {
		this.level_name = level_name;
		loadGameMaps();
	}

	private String level_name;

	////////// MAP OR LIST ////////////

	private GameList<ImageMap> gameMap_data_list = new GameList<ImageMap>(LISTTYPE.ARRAY);

	private void loadGameMaps() {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.getHandler().isCameraDependant() == false)
				continue;
			
			BufferedImage img = new ImageTask().loadImage("maps/" + level_name + "/" + tempLayer.getName() + ".png", false);
			ImageMap tempGameMap = new ImageMap(tempLayer, img);
			gameMap_data_list.addObject(tempGameMap);
		}
	}
	
	public ImageMap getGameMap(LAYER layer) {
		for (ImageMap tempMap : gameMap_data_list.getList()) {
			if (tempMap.getLayer() == layer)
				return tempMap;
		}
		return null;
	}

}
