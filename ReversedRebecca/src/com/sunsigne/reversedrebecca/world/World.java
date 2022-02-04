package com.sunsigne.reversedrebecca.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.ToolPlayerList;
import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.object.gui.GuiHealth;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.keyboard.WorldKeyboard;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class World implements Updatable {

	////////// SELF-CONTAINED ////////////

	private static World instance = null;

	public static World get() {
		return instance;
	}

	private void updateInstance() {
		if (instance != null)
			destroy();
		instance = this;
	}

	////////// WORLD ////////////

	public World(String levelName) {
		this(levelName, LAYER.GROUND);
	}

	public World(String levelName, LAYER layer) {
		LAYER.LOADING.addObject(new LoadingScreen());

		initParameters(levelName, layer);
		createMap();
		addControlers();
		start();

		LAYER.LOADING.getHandler().clear();

	}

	private void initParameters(String levelName, LAYER layer) {
		updateInstance();
		this.levelName = levelName;
		setLayer(layer);
	}

	private void createMap() {
		loadImageMap();
		new MapCreator().loadAllLevels(this);
		LAYER.GUI.addObject(new GuiHealth());
	}

	private void addControlers() {
		addKeyboardListener();
	}

	private void start() {
		getLayer(false).addObject(this);
		Game.getInstance().forceLoop();
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
		layers[1] = new LayerDualizer().getContentFromGround(layer);
	}

	////////// MAP OR LIST ////////////

	private GameList<BufferedImage> map_list = new GameList<BufferedImage>(LISTTYPE.ARRAY);

	private void loadImageMap() {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.getHandler().isCameraDependant() == false)
				break;

			BufferedImage img = new ImageTask().loadImage("maps/" + getLevelName() + "/" + tempLayer.getName() + ".png",
					false);
			map_list.addObject(img);
		}
	}

	public BufferedImage getImageMap(LAYER layer) {
		int index = 0;

		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer == layer)
				return map_list.getList().get(index);
			index++;
		}
		return new ImageTask().drawMissingTexture();
	}

	////////// USEFULL ////////////

	public void destroy() {
		for (LAYER tempLayer : LAYER.values()) {
			if (!tempLayer.getHandler().isCameraDependant() && !tempLayer.getName().contains("gui"))
				continue;

			tempLayer.getHandler().clear();
		}
		instance = null;
		ToolPlayerList.getList().clear();
		Game.getInstance().forceLoop();
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

	////////// KEYBOARD ////////////

	@SuppressWarnings("rawtypes")
	private void addKeyboardListener() {
		Class[] keyboards = new ForceInit().loadAllClassesInPackage(WorldKeyboard.class.getPackageName());
		new ForceInit().createInstanceOf(keyboards);
	}

}
