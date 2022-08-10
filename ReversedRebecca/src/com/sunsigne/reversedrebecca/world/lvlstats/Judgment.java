package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class Judgment {

	private String file = "menu.csv";
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

		String path = "maps/" + mapName + "/";
		FileTask file = new FileTask();

		if (file.doesExist(userData, path + "FAST.txt"))
			times[0] = Integer.parseInt(file.read(userData, path + "FAST.txt"));
		if (file.doesExist(userData, path + "METICULOUS.txt"))
			times[1] = Integer.parseInt(file.read(userData, path + "METICULOUS.txt"));

		return times;
	}

	private String getFast() {
		return stopWatch.getTime() < times[0] ? "Fast" : null;
	}

	private String getMeticulous() {
		return stopWatch.getTime() >= times[1] ? "Meticulous" : null;
	}

	private String getNice() {
		return (deed.getGoodWeight() != 0 & deed.getBadWeight() == 0) ? "Nice" : null;
	}

	private String getMean() {
		return (deed.getGoodWeight() == 0 & deed.getBadWeight() != 0) ? "Mean" : null;
	}

	private String getBipolar() {
		return (deed.getGoodWeight() != 0 & deed.getBadWeight() != 0) ? "Bipolar" : null;
	}

	private String getPragmatic() {
		return "Pragmatic";
	}

	private String youAre;

	private String loadYouAre() {
		if (getFast() != null)
			return getFast();

		if (getNice() != null)
			return getNice();

		if (getMean() != null)
			return getMean();

		if (getBipolar() != null)
			return getBipolar();

		if (getMeticulous() != null)
			return getMeticulous();

		return getPragmatic();
	}

	public String getYouAre() {
		youAre = loadYouAre();
		return new Translatable().getTranslatedText("LevelYouAre" + youAre, file);
	}

}
