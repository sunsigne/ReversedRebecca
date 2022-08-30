package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.physic.debug.DebugModeHandler;
import com.sunsigne.reversedrebecca.physic.finder.PathFinderOptimizer;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CorrelatedLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.IndependantLaw;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class Physic {

	public void loadRessources() {
		new ForceInit().loadAllClassesInPackage(IndependantLaw.class.getPackageName());
		// very demanding, must be added in a specific order !
		new CorrelatedLaw().loadRessources();
		LAYER.DEBUG.addObject(new PathFinderOptimizer());
		// because Debug Modes are alterned Physic Laws, they need be loaded AFTER
		new DebugModeHandler();
		new ForceInit().loadAllClassesInPackage(DebugMode.class.getPackageName());
	}

}
