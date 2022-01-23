package com.sunsigne.reversedrebecca.world;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.world.behaviors.WorldBehavior;
import com.sunsigne.reversedrebecca.world.keyboard.UseCanKeyQuit;
import com.sunsigne.reversedrebecca.world.updatable.GroundRendering;
import com.sunsigne.reversedrebecca.world.updatable.UpdateLayer;

public class World extends ExtraBehaviorsWorld {

	////////// SELF-CONTAINED ////////////

	private static World instance = null;

	public static World get() {
		return instance;
	}
	
	private void redefineInstance() {
		if (instance != null)
			instance.getHandler().removeObject(instance);
		instance = this;
	}

	////////// WORLD ////////////

	public World(String levelName) {
		this(levelName, LAYER.GROUND);
	}

	public World(String levelName, LAYER layer) {
		super(levelName, layer);
		redefineInstance();
		addWorldBehaviors();
		
		((UpdateLayer) updateLayer).createNewWorld(true);
	}

	////////// BEHAVIOR ////////////

	public WorldBehavior groundRendering;
	public WorldBehavior updateLayer;
	public WorldBehavior usecCanKeyQuit;

	private void addWorldBehaviors() {

		groundRendering = new GroundRendering(this);
		addBehavior(groundRendering);

		updateLayer = new UpdateLayer(this);
		addBehavior(updateLayer);
		
		usecCanKeyQuit = new UseCanKeyQuit(this);
		addBehavior(usecCanKeyQuit);
	}

}
