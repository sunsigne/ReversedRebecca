package com.sunsigne.reversedrebecca.world;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;

public class SuperExtraBehaviorsWorld {

	public SuperExtraBehaviorsWorld(String levelName, LAYER layer) {
		this.levelName = levelName;
		setLayer(layer);
		loadImageMap();
	}

	////////// NAME ////////////

	private String levelName;

	public String getLevelName() {
		return levelName;
	}

	////////// LAYER ////////////

	private LAYER[] layers = new LAYER[2];

	public LAYER getLayer(boolean contentType) {
		return contentType ? layers[1] : layers[0];
	}

	public void setLayer(LAYER layer) {
		layers[0] = layer;
		layers[1] = new LayerDualizer().getMap().get(layer);
	}

	////////// MAP OR LIST ////////////

	private GameList<ImageMap> imageMap_list = new GameList<ImageMap>(LISTTYPE.ARRAY);

	private void loadImageMap() {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.getHandler().isCameraDependant() == false)
				break;

			BufferedImage img = new ImageTask().loadImage("maps/" + getLevelName() + "/" + tempLayer.getName() + ".png",
					false);
			ImageMap tempImageMap = new ImageMap(tempLayer, img);
			imageMap_list.addObject(tempImageMap);
		}
	}

	public ImageMap getImageMap(LAYER layer) {
		for (ImageMap tempImageMap : imageMap_list.getList()) {
			if (tempImageMap.getLayer() == layer)
				return tempImageMap;
		}
		return null;
	}

}
