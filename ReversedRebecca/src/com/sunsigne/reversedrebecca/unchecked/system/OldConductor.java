package com.sunsigne.reversedrebecca.unchecked.system;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

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

		new TitleScreen();
		
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
		
		new ForceInit().loadAllClassesInPackage(Mappable.class.getPackageName());
		
		Game.getInstance().forceLoop();
	}

}
