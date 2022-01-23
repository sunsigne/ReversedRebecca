package com.sunsigne.reversedrebecca.world.updatable;

import com.sunsigne.reversedrebecca.object.gui.GuiHealth;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.world.ImageMap;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.behaviors.WorldTickBehavior;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class UpdateLayer implements WorldTickBehavior {

	public UpdateLayer(World world) {
		this.world = world;
		setLayer(getExtraBehaviorsWorld().getLayer(false));
	}

	////////// BEHAVIOR ////////////

	private World world;

	@Override
	public World getExtraBehaviorsWorld() {
		return world;
	}

	////////// LAYER ////////////

	private LAYER layer;

	public LAYER getLayer() {
		return layer;
	}

	public void setLayer(LAYER layer) {
		this.layer = layer;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (getLayer() == getExtraBehaviorsWorld().getLayer(false))
			return;
		
		removeOldWorld();
		createNewWorld();
	}

	private void removeOldWorld() {
		getLayer().getHandler().removeObject(getExtraBehaviorsWorld());
	}
	
	public void createNewWorld() {
		setLayer(getExtraBehaviorsWorld().getLayer(false));
		getLayer().addObject(getExtraBehaviorsWorld());
		
		LAYER content_layer = getExtraBehaviorsWorld().getLayer(true);
		ImageMap imageMap = getExtraBehaviorsWorld().getImageMap(content_layer);
		new MapCreator().loadLevel(imageMap);
		LAYER.GUI.addObject(new GuiHealth());
	}

}
