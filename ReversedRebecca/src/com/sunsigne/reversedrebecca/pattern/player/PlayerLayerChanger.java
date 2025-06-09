package com.sunsigne.reversedrebecca.pattern.player;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.physic.natural.independant.UpdateLayersLaw;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
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
		sendPlayerToCorrectIndex(player, content_layer);
		World.get().setLayer(ground_layer);
		((UpdateLayersLaw) new UpdateLayersLaw().getIndependantLaw()).forceUdpate();
	}

	private void sendPlayerToCorrectIndex(Player player, LAYER layer) {
		Handler handler = layer.getHandler();
		GameList<Updatable> cloneList = new ListCloner().deepClone(handler);

		for (int index = 0; index < cloneList.getList().size(); index++) {
			Updatable tempUpdatable = cloneList.getList().get(index);

			if (tempUpdatable instanceof PlayerClone == false)
				continue;

			handler.getList().add(index, player);
			Handler.updateHandlerMap(handler, player);
		}

		// if clone not found
		layer.addObject(player);
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
