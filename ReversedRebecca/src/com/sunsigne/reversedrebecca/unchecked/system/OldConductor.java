package com.sunsigne.reversedrebecca.unchecked.system;

import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class OldConductor {

	////////// START & STOP ////////////

	private static boolean running;

	public void startApp() {
		if (running)
			return;

		running = true;

		new Window(Game.getInstance());
		Game.getInstance().start();

		// DUAL CHECKING
//		new DualChecker().start();
//		Game.getInstance().forceLoop(); ??

		// LOADING OF MINIMAL RESSOURCES
		loadMinimalRessources();

		// LOADING OF RESSOURCES
		loadRessources();

		MapCreator map = new MapCreator();
		map.loadLevel();
		
	}

	public void stopApp() {
//		new DualChecker().stop();
		System.exit(1);
	}

	////////// RESSOURCES ////////////
	
	private void loadMinimalRessources() {
		
	}
	
	private void loadRessources() {

//		new ForceInit().loadClass(SheetBank.class);
//		new ForceInit().loadClass(ImageBank.class);
//		ImageBank.loadRessources();
		
		new ForceInit().loadAllClassesInPackage(PhysicLaw.class.getPackageName());
		new ForceInit().loadAllClassesInPackage(DebugMode.class.getPackageName());		
		
		Game.getInstance().forceLoop();
	}

}
