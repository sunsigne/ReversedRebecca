package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralKeyboard;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralMouse;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralScreen;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class HackComputer extends GameList<ProcessorObject> implements Updatable, RenderFree {

	public HackComputer(HackPuzzle puzzle) {
		super(LISTTYPE.LINKED);
		LAYER.PUZZLE.addObject(this);
		this.puzzle = puzzle;
	}

	////////// PUZZLE ////////////

	private HackPuzzle puzzle;

	public HackPuzzle getPuzzle() {
		return puzzle;
	}

	////////// MAP OR LIST ////////////

	private GameLimitedList<PeripheralObject> perif_list = new GameLimitedList<>(LISTTYPE.LINKED);

	@Override
	public void addObject(ProcessorObject object) {
		super.addObject(object);

		if (object instanceof PeripheralObject)
			perif_list.addObject((PeripheralObject) object);
	}

	@Override
	public void removeObject(ProcessorObject object) {
		super.removeObject(object);

		if (object instanceof PeripheralObject)
			perif_list.removeObject((PeripheralObject) object);
	}

	////////// PERIPHERAL ////////////

	private boolean keyboard;

	public boolean hasKeyboard() {
		return keyboard;
	}

	private boolean screen;

	public boolean hasScreen() {
		return screen;
	}

	private boolean mouse;

	public boolean hasMouse() {
		return mouse;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		keyboard = false;
		if (perif_list.containsObject(new PeripheralKeyboard(puzzle, 0, 0)))
			keyboard = true;

		screen = false;
		if (perif_list.containsObject(new PeripheralScreen(puzzle, 0, 0)))
			screen = true;

		mouse = false;
		if (perif_list.containsObject(new PeripheralMouse(puzzle, 0, 0)))
			mouse = true;

	}

}
