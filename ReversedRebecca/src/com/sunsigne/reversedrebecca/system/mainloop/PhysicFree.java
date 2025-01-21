package com.sunsigne.reversedrebecca.system.mainloop;

import com.sunsigne.reversedrebecca.physic.PhysicLaw;

public interface PhysicFree extends Updatable {
	
	////////// PHYSICS ////////////

	@Override
	public default PhysicLaw[] getPhysicLinker() {
		return null;
	};
	
}
