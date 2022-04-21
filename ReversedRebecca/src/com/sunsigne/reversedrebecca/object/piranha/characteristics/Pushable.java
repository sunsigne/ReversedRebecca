package com.sunsigne.reversedrebecca.object.piranha.characteristics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator;

public interface Pushable extends SpeedVariator {

	////////// PUSHABLE ////////////

	public boolean isBeingPushed();

	public void setIsBeingPushed(boolean isBeingPushed);

}
