package com.sunsigne.reversedrebecca.object.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.GameTimer;

public interface PathSearcher extends Velocity {

	////////// PATH FINDER ////////////

	public default boolean mustFollowPath() {
		return true;
	}

	public Position getGoal();

	public void setGoal(Position goal);

	public DIRECTION getPath();

	public void setPath(DIRECTION path);

	public boolean isPathFinderDisabled();

	public void enablePathFinder();

	public void disabledPathFinder();

	public default void disabledPathFinder(int timeInTicks) {
		if(isPathFinderDisabled())
			return;
		
		disabledPathFinder();
		new GameTimer(timeInTicks, () -> {
			enablePathFinder();
		});
	}

}
