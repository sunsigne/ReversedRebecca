package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Judgment {

	private boolean userData = false;

	public Judgment(String mapName, StopWatch stopWatch, Deed deed) {
		times = loadTimes(mapName);
		this.stopWatch = stopWatch;
		this.deed = deed;
	}

	////////// YOU ARE ////////////

	private int[] times;
	private StopWatch stopWatch;
	private Deed deed;

	private int[] loadTimes(String mapName) {
		int[] times = new int[] { 0, 0 };

		String path = "maps/" + mapName + "/" + "TIME.txt";
		FileTask file = new FileTask();

		if (file.doesExist(userData, path) == false)
			return times;

		times[0] = Integer.parseInt(file.read(userData, "FAST", path));
		times[1] = Integer.parseInt(file.read(userData, "METICULOUS", path));

		return times;
	}

	private YOUARE youAre;

	private YOUARE loadYouAre() {
		if (stopWatch.getTime() < times[0])
			return YOUARE.FAST;

		if (deed.getBadWeight() >= 99)
			return YOUARE.PSYCHOPATH;

		if (deed.getBadWeight() > deed.getGoodWeight())
			return YOUARE.MEAN;

		if (deed.getGoodWeight() > deed.getBadWeight())
			return YOUARE.NICE;

		if (stopWatch.getTime() >= times[1])
			return YOUARE.METICULOUS;

		return YOUARE.PRAGMATIC;
	}

	protected boolean isPsychoPath() {
		youAre = loadYouAre();
		return youAre == YOUARE.PSYCHOPATH;
	}

	public String getYouAre() {
		youAre = loadYouAre();
		return new Translatable().getTranslatedText("LevelYouAre" + youAre.getName(), FilePath.MENU);
	}

}
