package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class LevelStats {

	private boolean userData = false;

	public LevelStats(String mapName) {
		deed = new Deed();
		loadDeedsLimits(mapName);
	}

	////////// PUZZLE COUNT ////////////
	/*
	 * private int puzzleCount;
	 * 
	 * public void addPuzzleCount() { puzzleCount++; }
	 * 
	 * public String getPuzzleCount() { return String.valueOf(puzzleCount); }
	 */
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
