package com.sunsigne.reversedrebecca.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.characteristics.CharacteristicList;
import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.HUDList;
import com.sunsigne.reversedrebecca.object.piranha.SetupObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.piranha.Piranha;
import com.sunsigne.reversedrebecca.piranha.condition.global.TimeCondition;
import com.sunsigne.reversedrebecca.piranha.request.memory.MemorySet;
import com.sunsigne.reversedrebecca.piranha.request.memory.SaveEraserList;
import com.sunsigne.reversedrebecca.piranha.request.memory.SaveList;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.Textures;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.controllers.WorldControllers;
import com.sunsigne.reversedrebecca.world.lvlstats.LevelStats;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class World implements Updatable, RenderFree {

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

	private boolean userdata = false;
	
	public World(String mapName) {
		if (LAYER.LOADING.getHandler().getList().isEmpty())
			LAYER.LOADING.addObject(new LoadingScreen());

		initParameters(mapName);
		createMap();
		updateLayer();

		addSetup();
		if (instance != this)
			return;
		new Save().loadSave();
		if (instance != this)
			return;

		addHUD();
		addControllers();
		start();

		LAYER.LOADING.getHandler().clear();

	}

	private void initParameters(String mapName) {
		updateInstance();
		this.mapName = mapName;
		setLayer(LAYER.GROUND);
	}

	private void createMap() {
		loadImageMap();
		new MapCreator().loadAllLayers(this);
		new Piranha().optimize();
		updateLivingAbove();
	}

	private void updateLivingAbove() {

		// apply to all Layers
		for (LAYER tempLayer : LAYER.values()) {

			Handler handler = tempLayer.getHandler();
			var list = new GameList<Updatable>(LISTTYPE.LINKED);

			// search for LivingObject
			for (Updatable tempUpdatable : handler.getList()) {
				if (tempUpdatable instanceof LivingObject == false)
					continue;

				// add the LivingObject to a temp list
				list.addObject(tempUpdatable);
			}

			// besure the LivingObjects are at the end of the list
			for (Updatable tempUpdatable : list.getList()) {
				handler.softRemoveObject(tempUpdatable);
				handler.addObject(tempUpdatable);
			}
		}
	}

	private void updateLayer() {
		Game.getInstance().forceLoop();

		Player player = new PlayerFinder().getPlayer();
		if (player == null || player.getHandler() == null)
			return;

		// determine ground of the player layer
		LAYER ground_player_layer = LAYER.GROUND;
		for (LAYER tempLayer : LAYER.values()) {
			if (player.getHandler() != tempLayer.getHandler())
				ground_player_layer = tempLayer;

			else
				break;
		}

		// if player and world are on the same layer
		if (ground_player_layer == getLayer(false))
			return;

		// set the world to the layer where the player stands
		setLayer(ground_player_layer);
	}

	private void addSetup() {
		levelStats = new LevelStats(mapName);
		getLayer(true).addObject(new SetupObject());
		new TimeCondition().registerValue(0);
		Game.getInstance().forceLoop();
	}

	private void addHUD() {
		new CharacteristicList().reset();

		for (HUD tempHUD : HUDList.getList().getList()) {
			LAYER.HUD.getHandler().getList().add(0, tempHUD);
			Handler.updateHandlerMap(LAYER.HUD.getHandler(), tempHUD);
		}
	}

	private void addControllers() {
		new ForceInit().loadAllClassesInPackage(WorldControllers.class.getPackageName());
	}

	private LevelStats levelStats;

	public LevelStats getLevelStats() {
		return levelStats;
	}

	private void start() {
		boolean frozen = isFrozen();
		freeze(true);
		getLayer(false).addObject(this);
		freeze(false, !frozen);
		Game.getInstance().forceLoop();
	}

	private void stopApp() {
		new SoundTask().playSound(SOUNDTYPE.ERROR, "error");
		JOptionPane.showMessageDialog(null,
				"An error has occurred : map folder \"" + mapName + "\" incorrect format or missing");
		new Conductor().stopApp();
	}
	
	////////// NAME ////////////

	private String mapName;

	public String getMapName() {
		return mapName;
	}

	////////// SIZE ////////////

	private int width, height;

	public int getWidth() {
		if (width == 0) {
			if (map_list.getList().isEmpty())
				return 0;

			int size = map_list.getList().size();
			int tempWidth = 0;

			for (int index = 0; index < size; index++) {
				tempWidth = map_list.getList().get(index).getWidth();
				if (tempWidth != 1)
					break;
			}

			width = tempWidth * Size.M / 16;
		}
		return width;
	}

	public int getHeight() {
		if (height == 0) {
			if (map_list.getList().isEmpty())
				return 0;

			int size = map_list.getList().size();
			int tempHeight = 0;

			for (int index = 0; index < size; index++) {
				tempHeight = map_list.getList().get(index).getHeight();
				if (tempHeight != 1)
					break;
			}

			height = tempHeight * Size.M / 16;
		}
		return height;
	}

	////////// LAYER ////////////

	private LAYER[] layers = new LAYER[3];

	public LAYER getLayer(boolean contentType) {
		return contentType ? layers[1] : layers[0];
	}

	public LAYER getLightLayer() {
		return layers[2];
	}

	public void setLayer(LAYER ground_layer) {
		layers[0] = ground_layer;
		layers[1] = new LayerDualizer().getContentFromGround(ground_layer);
		layers[2] = new LayerDualizer().getLightFromContent(layers[1].getHandler());
	}

	////////// MAP OR LIST ////////////

	private GameList<BufferedImage> map_list = new GameList<BufferedImage>(LISTTYPE.ARRAY);

	private void loadImageMap() {
		String mapName = this.mapName;

		if(new FileTask().doesExist(userdata, "maps/" + mapName) == false)
			stopApp();
				
		if (mapName.contains("workshop_")) {
			String name = mapName.split("workshop_")[1];
			new Textures().loadRessources("textures/workshop/" + name);
		}

		if (mapName.equals(FilePath.TEST)) {
			boolean exist = false;
			COLOR color = null;
			do {
				COLOR[] colors = COLOR.values();
				color = colors[new Random().nextInt(colors.length)];
				exist = new FileTask().doesExist(false, "maps/" + mapName.concat("/" + color.getName()));
			} while (exist == false);
			mapName = mapName.concat("/" + color.getName());
		}

		for (LAYER tempLayer : LAYER.values()) {
			if (!tempLayer.isMapLayer())
				continue;

			BufferedImage img = new ImageTask().loadImage("maps/" + mapName + "/" + tempLayer.getName(), true);
			if (img == null)
				img = drawBlackSquare();
			map_list.addObject(img);
		}
	}

	private BufferedImage drawBlackSquare() {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, 1, 1);
		g2d.dispose();
		return img;
	}

	public BufferedImage getImageMap(LAYER layer) {
		int index = 0;

		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer == layer)
				return map_list.getList().get(index);
			index++;
		}
		return drawBlackSquare();
	}

	////////// USEFULL ////////////

	private boolean freeze;

	public boolean isFrozen() {
		return freeze;
	}

	public void freeze(boolean freeze) {
		freeze(freeze, !freeze);
	}

	public void freeze(boolean freeze, boolean playerCanInteract) {
		this.freeze = freeze;

		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.isMapLayer())
				tempLayer.getHandler().setFreezeTicking(freeze);
		}

		new PlayerFinder().setPlayerCanInteract(playerCanInteract);

		boolean flag = false;
		if (flag && freeze) // when active, remove fading menu if froze before completed
			((FadeMenuLaw) PhysicList.getList().getObject(new FadeMenuLaw())).setFading(false);
	}

	public void destroy() {
		freeze(true);
		new Cutscene().stop(false);
		closePuzzle();
		resetLayers();
		instance = null;
		MemorySet.getSet().clear();
		SaveList.getList().clear();
		SaveEraserList.getList().clear();
		Game.getInstance().forceLoop();
		freeze(false);
	}

	private void closePuzzle() {
		for (Updatable tempObject : LAYER.PUZZLE.getHandler().getList()) {
			if (tempObject instanceof Puzzle == false)
				continue;

			Puzzle puzzleObject = (Puzzle) tempObject;
			puzzleObject.closePuzzle(false);
		}
	}

	private void resetLayers() {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer == LAYER.DEBUG)
				break;

			tempLayer.getHandler().clear();
		}
	}

	////////// TICK ////////////

	private int frame = Game.SEC;
	private int time;

	public int getTime() {
		return time;
	}

	@Override
	public void tick() {
		frame--;

		if (frame <= 0) {
			frame = Game.SEC;
			time++;
			new TimeCondition().registerValue(time);
		}
	}

}
