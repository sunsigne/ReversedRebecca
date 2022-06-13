package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class AntivirusSpawner extends AntivirusObject {

	public AntivirusSpawner(Puzzle puzzle, ProcessorFolder folder) {
		super(puzzle, "Spawner");
		this.folder = folder;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "spawner";
	}

	////////// ANTIVIRUS ////////////

	private ProcessorFolder folder;

	@Override
	public void antivirusAction() {
		if (folder.isFull())
			return;

		createLocker();

	}

	private void createLocker() {
		AntivirusLocker locker = new AntivirusLocker(getPuzzle(), folder);
		folder.push(true, locker);
		playSound("bip_short");
	}

	@Override
	public void destroyAntivirus() {
	}

	////////// TICK ////////////

	private final int SPAWNING_TIME = 100;
	private int time = 0;

	@Override
	public void tick() {
		time--;
		if (time < 0) {
			time = SPAWNING_TIME;
			antivirusAction();
		}
	}

}
