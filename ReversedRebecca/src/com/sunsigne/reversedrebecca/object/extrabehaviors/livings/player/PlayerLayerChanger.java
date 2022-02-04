package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player;

import com.sunsigne.reversedrebecca.pattern.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.world.World;

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

	public void goes(LAYER ground_layer) {
		Player player = new PlayerFinder().getPlayer();

		if (player == null)
			return;

		LAYER content_layer = new LayerDualizer().getContentFromGround(ground_layer);

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
				goes(list.getList().get(index + 1));
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
				goes(list.getList().get(index - 3));
				return;
			}
		}
	}

}
