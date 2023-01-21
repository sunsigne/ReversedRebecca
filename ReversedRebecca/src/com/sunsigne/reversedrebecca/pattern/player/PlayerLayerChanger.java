package com.sunsigne.reversedrebecca.pattern.player;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.mapcreator.GroundRendering;

public class PlayerLayerChanger {

	public PlayerLayerChanger() {
		createList();
	}

	////////// MAP OR LIST ////////////

	private GameList<LAYER> list = new GameList<>(LISTTYPE.ARRAY);

	private void createList() {
		for (LAYER tempLayer : LAYER.values()) {
			if (!tempLayer.isMapLayer())
				continue;

			list.addObject(tempLayer);
		}
	}

	////////// LAYER ////////////

	private boolean layerDoesNotExist(LAYER ground_layer) {
		GroundRendering groundRedering = (GroundRendering) ground_layer.getHandler().getList().get(0);
		BufferedImage img = groundRedering.getWorld().getImageMap(ground_layer);
		// this happens when no image for the Layer exist in the lvl folder, a "fake
		// image" of 1 pixel is generated
		return img.getWidth() == 1;
	}

	public void goes(LAYER ground_layer) {
		Player player = new PlayerFinder().getPlayer();

		if (player == null)
			return;

		LAYER content_layer = new LayerDualizer().getContentFromGround(ground_layer);

		if (layerDoesNotExist(ground_layer))
			return;

		player.getHandler().softRemoveObject(player);
		content_layer.addObject(player);
		World.get().setLayer(ground_layer);
	}

	public void goesUp() {
		Player player = new PlayerFinder().getPlayer();

		if (player == null)
			return;

		int size = list.getList().size();

		for (int index = 0; index < size - 1; index++) {
			if (player.getHandler() == list.getList().get(index).getHandler()) {
				goes(list.getList().get(index + 2));
				return;
			}

		}
	}

	public void goesDown() {
		Player player = new PlayerFinder().getPlayer();

		if (player == null)
			return;

		int size = list.getList().size();

		for (int index = 3; index < size; index++) {
			if (player.getHandler() == list.getList().get(index).getHandler()) {
				goes(list.getList().get(index - 4));
				return;
			}
		}
	}

}
