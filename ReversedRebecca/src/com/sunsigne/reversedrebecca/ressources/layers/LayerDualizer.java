package com.sunsigne.reversedrebecca.ressources.layers;

import java.util.HashMap;

import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class LayerDualizer {

	////////// MAP OR LIST ////////////

	private static HashMap<LAYER, LAYER> map = new HashMap<>();

	public LAYER getContentFromGround(LAYER ground_layer) {
		return map.get(ground_layer);
	}

	public LAYER getGroundFromContent(Handler handler) {
		LAYER ground_layer = LAYER.GROUND;

		for (LAYER tempLayer : LAYER.values()) {
			if (handler != tempLayer.getHandler())
				ground_layer = tempLayer;

			else
				break;
		}

		return ground_layer;
	}

	public LAYER getLightFromContent(Handler handler) {
		LAYER light_layer = LAYER.LIGHT;
		boolean flag = false;

		for (LAYER tempLayer : LAYER.values()) {
			if (handler == tempLayer.getHandler()) {
				flag = true;
				continue;
			}

			if (flag) {
				light_layer = tempLayer;
				break;
			}
		}

		return light_layer;
	}

	////////// USEFULL ////////////

	public void dualizeSameFloorLayers() {
		LAYER ground = null;
		LAYER content = null;

		int index = 1;

		for (LAYER tempLayer : LAYER.values()) {
			if (!tempLayer.getHandler().isCameraDependant())
				break;

			if (tempLayer.getName().contains("light"))
				continue;

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
