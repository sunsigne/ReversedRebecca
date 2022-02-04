package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class UpdateLayersLaw implements PhysicLaw {

	////////// PHYSIC LAW ////////////

	public UpdateLayersLaw() {
		PhysicList.getList().addObject(this);
	}

	private static PhysicLaw physicLaw = new UpdateLayersLaw();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
	}

	////////// TICK ////////////

	private LAYER layer;

	@Override
	public void tick(Updatable object) {
		if (World.get() == null) {
			layer = LAYER.DEBUG;
			return;
		}

		if (object instanceof World == false)
			return;

		World world = (World) object;

		if (layer == world.getLayer(false))
			return;

		updateLayerVisibilities(world);
		registerNewLayer(world);
	}

	public void updateLayerVisibilities(World world) {
		boolean hideRendering = false;

		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer == LAYER.WORLD_TEXT)
				continue;

			if (!tempLayer.getHandler().isCameraDependant())
				continue;

			tempLayer.getHandler().setHideRendering(hideRendering);

			if (world.getLayer(true) == tempLayer)
				hideRendering = true;
		}
	}

	private void registerNewLayer(World world) {
		layer.getHandler().removeObject(world);
		layer = world.getLayer(false);
		layer.addObject(world);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}