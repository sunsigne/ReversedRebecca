package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.gui.GUI;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.NaturalLaws;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.piranha.Piranha;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class Conductor {

	////////// START & STOP ////////////

	public void startApp() {

		LAYER.LOADING.addObject(new LoadingScreen());
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);
		new Window(Game.getInstance());
		Game.getInstance().start();
		new DualChecker();

		// LOADING OF MINIMAL RESSOURCES
		loadMinimalRessources();
		Game.getInstance().forceLoop();

		// LOADING OF RESSOURCES
		loadRessources();
		Game.getInstance().forceLoop();

		new TitleScreen();
		LAYER.LOADING.getHandler().clear();
	}

	public void stopApp() {
		System.exit(1);
	}

	////////// RESSOURCES ////////////

	private void loadMinimalRessources() {
		Language.getInstance();
	}

	private void loadRessources() {
		
		new LayerDualizer().dualizeSameFloorLayers();
		new ForceInit().loadAllClassesInPackage(GUI.class.getPackageName());

		// very demanding, must be added in a specific order !
		new NaturalLaws().loadRessources();
		// because Debug Modes are alterned Physic Laws, they have to be loaded AFTER
		new ForceInit().loadAllClassesInPackage(DebugMode.class.getPackageName());

		// be sure to load this BEFORE Piranha
		new ForceInit().loadAllClassesInPackage(Mappable.class.getPackageName());
		new Piranha().loadRessources();
	}

}
