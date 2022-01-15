package com.sunsigne.reversedrebecca.object.extrabehaviors;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface Behavior extends Updatable, CollisionReactor {

	////////// BEHAVIOR ////////////
	
	ExtraBehaviorsObject getExtraBehaviorsObject();

}
