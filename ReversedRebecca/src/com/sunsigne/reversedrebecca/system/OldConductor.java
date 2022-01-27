package com.sunsigne.reversedrebecca.system;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class OldConductor {

	////////// START & STOP ////////////

	public void startApp() {

		new Window(Game.getInstance());
		Game.getInstance().start();
		new DualChecker();

		// LOADING OF MINIMAL RESSOURCES
		loadMinimalRessources();

		// LOADING OF RESSOURCES
		loadRessources();

		new TitleScreen();
		
	}

	public void stopApp() {
		System.exit(1);
	}

	////////// RESSOURCES ////////////
	
	private void loadMinimalRessources() {
		Language.getInstance();
	}
	
	private void loadRessources() {

//		new ForceInit().createInstanceOf(SheetBank.class);
//		new ForceInit().createInstanceOf(ImageBank.class);
//		ImageBank.loadRessources();
		
		new LayerDualizer().dualizeSameFloorLayers();
		
		new ForceInit().loadAllClassesInPackage(PhysicLaw.class.getPackageName());
		// because Debug Modes are alterned Physic Laws, they have to be loaded AFTER
		new ForceInit().loadAllClassesInPackage(DebugMode.class.getPackageName());		
		
		new ForceInit().loadAllClassesInPackage(Mappable.class.getPackageName());
		
		
		
		Game.getInstance().forceLoop();
	}

}
