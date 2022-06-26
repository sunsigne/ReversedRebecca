package com.sunsigne.reversedrebecca.world;

import com.sunsigne.reversedrebecca.menu.lvlcomplete.StopWatch;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class LevelEndStats {

	protected LevelEndStats() {
		LAYER.STATS.getHandler().clear();
		createStopWatch();
	}

	////////// STOPWATCH ////////////

	private StopWatch stopWatch;

	public StopWatch getStopWatch() {
		return stopWatch;
	}

	private void createStopWatch() {
		stopWatch = new StopWatch();
		LAYER.STATS.addObject(stopWatch);
	}

	public String getTime() {
		return String.format("%.2f", stopWatch.getTime());
	}

}
