package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ProcessorFolder extends ProcessorObject {

	public ProcessorFolder(Puzzle puzzle, String text, int x, int y, ProcessorObject... processors) {
		super(puzzle, text, x, y);

		size = processors.length;
		this.processors = new ProcessorObject[size];
		for (int index = 0; index < size; index++) {
			this.processors[index] = processors[index];
		}
	}

	////////// NAME ////////////

	public String getName() {
		return "folder";
	}

	////////// VIRUS ACTION ////////////

	private ProcessorObject[] processors;
	private int size;

	@Override
	public void doVirusAction() {
		hideOldFolder();
		displayNewFolder();
		new SoundTask().play(SOUNDTYPE.SOUND, "sound/virus_nav");
	}

	protected void hideOldFolder() {
		Handler handler = LAYER.PUZZLE.getHandler();
		var list = new GameList<Updatable>(LISTTYPE.LINKED);

		// remove all processor from the handler
		for (Updatable tempUpdatable : handler.getList()) {
			if (tempUpdatable instanceof ProcessorObject)
				list.addObject(tempUpdatable);
		}

		for (Updatable tempUpdatable : list.getList()) {
			handler.removeObject(tempUpdatable);
		}
	}

	protected void displayNewFolder() {
		// add to the handler all processors contained in the folder ...
		for (int index = 0; index < size; index++) {
			// ... only if still in the computer
			if (getComputer().containsObject(processors[index]))
				LAYER.PUZZLE.addObject(processors[index]);
		}
	}

}
