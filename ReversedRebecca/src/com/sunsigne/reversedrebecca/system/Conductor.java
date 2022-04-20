package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.gui.GUI;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.physic.laws.CollisionLaw;
import com.sunsigne.reversedrebecca.physic.laws.MoveTowardGoalLaw;
import com.sunsigne.reversedrebecca.physic.laws.PathFindingLaw;
import com.sunsigne.reversedrebecca.physic.laws.RoundToTileLaw;
import com.sunsigne.reversedrebecca.physic.laws.UpdateLayersLaw;
import com.sunsigne.reversedrebecca.physic.laws.VelocityLaw;
import com.sunsigne.reversedrebecca.piranha.Piranha;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class Conductor {

	////////// START & STOP ////////////

	public void startApp() {

		LAYER.LOADING.addObject(new LoadingScreen());
		new GameCursor().setVisible(true);
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

		// king of independant, can be loaded first.
		new LayerDualizer().dualizeSameFloorLayers();

		// same idea
		new ForceInit().loadAllClassesInPackage(GUI.class.getPackageName());

		// same, but be sure to load this BEFORE Piranha
		new ForceInit().loadAllClassesInPackage(Mappable.class.getPackageName());

		// very demanding, must be added in a specific order !
		loadPhysicLaws();

		// because Debug Modes are alterned Physic Laws, they have to be loaded AFTER
		new ForceInit().loadAllClassesInPackage(DebugMode.class.getPackageName());

		// better to add this AFTER Mappable, just in case
		new Piranha().loadRessources();

	}

	private void loadPhysicLaws() {
		// This law as nothing to do close or far with movement
		PhysicList.getList().addObject(new UpdateLayersLaw());

		// (following rules CANNOT be added in another order)

		// An object can move
		PhysicList.getList().addObject(new VelocityLaw());

		// An object can move by itself, following path.
		PhysicList.getList().addObject(new MoveTowardGoalLaw());

		// Moving, an object that meets an obstacle is shifted.
		PhysicList.getList().addObject(new CollisionLaw());

		// An object is repositionnated properly for calculating pathfinding.
		PhysicList.getList().addObject(new RoundToTileLaw());

		// Calculating path of the object for its next move.
		PhysicList.getList().addObject(new PathFindingLaw());
	}

}
