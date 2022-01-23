package com.sunsigne.reversedrebecca.ressources.layers;

import java.util.HashMap;

public class LayerDualizer {

	////////// MAP OR LIST ////////////
	
	private static HashMap<LAYER, LAYER> map = new HashMap<>();

	public HashMap<LAYER, LAYER> getMap() {
		return map;
	}
	
	////////// USEFULL ////////////
	
	public void dualizeSameFloorLayers() {
		LAYER ground = null;
		LAYER content = null;

		int index = 1;

		for (LAYER tempLayer : LAYER.values()) {
			if (!tempLayer.getHandler().isCameraDependant())
				break;

			index++;

			if (index % 2 == 0) {
				ground = tempLayer;
				continue;
			}

			else {
				content = tempLayer;
				map.put(ground, content);
			}
		}
	}

}
