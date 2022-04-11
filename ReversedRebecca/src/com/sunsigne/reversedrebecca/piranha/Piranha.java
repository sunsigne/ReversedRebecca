package com.sunsigne.reversedrebecca.piranha;

import com.sunsigne.reversedrebecca.pattern.ForceInit;

public class Piranha {

	public void loadRessources() {
		new ForceInit().loadAllClassesInPackage(Piranha.class.getPackageName());
	}

}
