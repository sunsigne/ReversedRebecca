package com.sunsigne.reversedrebecca.world;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class WorldHolder implements Updatable {

	public WorldHolder(String level_name) {
		this.level_name = level_name;
		loadGameMaps();
	}

	private String level_name;

	////////// TEXTURE ////////////

	private GameList<GameMap> gameMap_data_list = new GameList<GameMap>(LISTTYPE.ARRAY);

	private void loadGameMaps() {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.getHandler().isCameraDependant() == false)
				continue;
			
			BufferedImage img = new ImageTask().loadImage("maps/" + level_name + "/" + tempLayer.getName() + ".png", false);
			GameMap tempGameMap = new GameMap(tempLayer, img);
			gameMap_data_list.addObject(tempGameMap);
		}
	}
	
	public GameMap getGameMap(LAYER layer) {
		for (GameMap tempMap : gameMap_data_list.getList()) {
			if (tempMap.getLayer() == layer)
				return tempMap;
		}
		return null;
	}

	public BufferedImage getImage() {
		for (GameMap tempMap : gameMap_data_list.getList()) {
			if (tempMap.getLayer().getHandler() == getHandler())
				return tempMap.getImage();
		}
		return new ImageTask().drawMissingTexture();
	}

}
