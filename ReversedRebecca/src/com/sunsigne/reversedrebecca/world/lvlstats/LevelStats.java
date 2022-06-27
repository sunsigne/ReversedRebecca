package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LevelStats {

	public LevelStats(String mapName) {
		createStopWatch(mapName);
		createDeed();
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

	private void createStopWatch(String mapName) {
		removeExistingStopWatch();
		int[] time = getTimes(mapName);
		stopWatch = new StopWatch(time[0], time[1]);
		LAYER.DEBUG.addObject(stopWatch);
	}

	private int[] getTimes(String mapName) {
		int[] times = new int[] { 0, 0 };

		String path = "maps/" + mapName + "/";
		FileTask file = new FileTask();

		if (file.doesExist(path + "FAST.txt"))
			times[0] = Integer.parseInt(file.read(path + "FAST.txt"));
		if (file.doesExist(path + "METICULOUS.txt"))
			times[1] = Integer.parseInt(file.read(path + "METICULOUS.txt"));

		return times;
	}

	public String getTime() {
		return String.format("%.2f", stopWatch.getTime());
	}

	////////// PUZZLE COUNT ////////////

	private int puzzleCount;

	public void addPuzzleCount() {
		puzzleCount++;
	}

	public String getPuzzleCount() {
		return String.valueOf(puzzleCount);
	}

	////////// DEED ////////////

	private Deed deed;

	public Deed getDeed() {
		return deed;
	}

	private void createDeed() {
		deed = new Deed();
	}

	public String getGoodDeed() {
		return deed.getGoodDeed();
	}

	public String getBadDeed() {
		return deed.getBadDeed();
	}

	////////// YOU ARE ////////////

	private String file = "menu.csv";

	private String translate(String text) {
		return new Translatable().getTranslatedText("LevelYouAre" + text, file);
	}

	public String getYouAre() {

		// is fast
		if (stopWatch.isFast())
			return translate("Fast");

		// has good karma
		if (deed.hasGoodKarma() && deed.hasBadKarma() == false)
			return translate("Nice");

		// has bad karma
		if (deed.hasGoodKarma() == false && deed.hasBadKarma())
			return translate("Mean");

		// has both good & bad karma
		if (deed.hasGoodKarma() && deed.hasBadKarma())
			return translate("Bipolar");

		// is slow
		if (stopWatch.isMeticulous())
			return translate("Meticulous");

		return translate("Pragmatic");
	}

}
