package com.sunsigne.reversedrebecca.physic;

import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CorrelatedLaw;
import com.sunsigne.reversedrebecca.physic.natural.independant.IndependantLaw;

public class Physic {

	public void loadRessources() {
		new ForceInit().loadAllClassesInPackage(IndependantLaw.class.getPackageName());
		// very demanding, must be added in a specific order !
		new CorrelatedLaw().loadRessources();
		// because Debug Modes are alterned Physic Laws, they need be loaded AFTER
		new ForceInit().loadAllClassesInPackage(DebugMode.class.getPackageName());
	}

}
