package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class LevelStats {

	private boolean userData = false;

	public LevelStats(String mapName) {
		deed = new Deed();
		loadCounter();
		loadDeedsLimits(mapName);
	}

	////////// COUNTER ////////////

	private Counter counter1;
	private Counter counter2;
	private Counter counter3;

	public Counter getCounter(int index) {
		if (index == 1)
			return counter1;
		if (index == 2)
			return counter2;
		if (index == 3)
			return counter3;
		return null;
	}

	private void loadCounter() {
		counter1 = new Counter("Custom Count 1", 0);
		counter2 = new Counter("Custom Count 2", 0);
		counter3 = new Counter("Custom Count 3", 0);
	}

	////////// DEED ////////////

	private Deed deed;

	public Deed getDeed() {
		return deed;
	}

	////////// YOU ARE ////////////

	private int sadisticLimit = -10;
	private int angelicLimit = 10;

	private void loadDeedsLimits(String mapName) {
		String path = "maps/" + mapName + "/" + "DEED.txt";
		FileTask file = new FileTask();

		if (file.doesExist(userData, path) == false)
			return;

		sadisticLimit = Integer.parseInt(file.read(userData, "SADISTIC", path));
		angelicLimit = Integer.parseInt(file.read(userData, "ANGELIC", path));
	}

	public YOUARE getYouAre() {
		int karma = deed.getKarmaWeight();

		if (karma <= -999)
			return YOUARE.PSYCHOPATH;
		if (karma <= sadisticLimit)
			return YOUARE.SADISCTIC;
		if (karma >= angelicLimit)
			return YOUARE.ANGELIC;
		if (karma < 0)
			return YOUARE.MEAN;
		if (karma > 0)
			return YOUARE.NICE;

		return YOUARE.NEUTRAL; // -> karma == 0;
	}

}
