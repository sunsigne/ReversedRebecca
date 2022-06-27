package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LevelStats {

	public LevelStats(String mapName) {
		createStopWatch();
		createDeed();
		createJudgment(mapName);
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

	private Judgment judgement;

	public Judgment getJudgment() {
		return judgement;
	}

	private void createJudgment(String mapName) {
		judgement = new Judgment(mapName, stopWatch, deed);
	}

	public String getYouAre() {
		return judgement.getYouAre();
	}

}
