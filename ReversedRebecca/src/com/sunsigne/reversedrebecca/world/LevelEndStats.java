package com.sunsigne.reversedrebecca.world;

import com.sunsigne.reversedrebecca.menu.lvlcomplete.StopWatch;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LevelEndStats {

	protected LevelEndStats() {
		createStopWatch();
	}

	////////// STOPWATCH ////////////

	private StopWatch stopWatch;

	public StopWatch getStopWatch() {
		return stopWatch;
	}

	private void removeExistingStopWatch() {
		for (Updatable tempUpdatable : LAYER.DEBUG.getHandler().getList()) {
			if (tempUpdatable instanceof StopWatch == false)
				continue;

			LAYER.DEBUG.getHandler().removeObject(tempUpdatable);
		}
	}
	
	private void createStopWatch() {
		removeExistingStopWatch();
		stopWatch = new StopWatch();
		LAYER.DEBUG.addObject(stopWatch);
	}

	public String getTime() {
		return String.format("%.2f", stopWatch.getTime());
	}

}
