package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class AntivirusSpawner extends AntivirusObject {

	public AntivirusSpawner(Puzzle puzzle, ProcessorFolder folder) {
		super(puzzle, new Translatable().getTranslatedText("Antivirus" + "Spawner", FilePath.PUZZLE));
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

	private final int SPAWNING_TIME = 210;
	private int time = 0;

	@Override
	public void tick() {
		super.tick();
		
		time--;
		if (time < 0) {
			time = SPAWNING_TIME;
			antivirusAction();
		}
	}

}
