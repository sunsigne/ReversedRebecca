package com.sunsigne.reversedrebecca.piranha;

import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;

public class Piranha {

	public void loadRessources() {
		new ForceInit().loadAllClassesInPackage(Piranha.class.getPackageName());
	}

	////////// OPTIMIZATION ////////////
	
	public void optimize() {
		new GlobalInstructionList().optimize();
	}

}
