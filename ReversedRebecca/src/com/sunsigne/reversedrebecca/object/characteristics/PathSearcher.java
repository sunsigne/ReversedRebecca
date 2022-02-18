package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;

public interface PathSearcher extends Velocity {

	////////// PATH FINDER ////////////

	public Position getGoal();

	public void setGoal(Position goal);

	public DIRECTION getPath();

	public void setPath(DIRECTION path);

}
