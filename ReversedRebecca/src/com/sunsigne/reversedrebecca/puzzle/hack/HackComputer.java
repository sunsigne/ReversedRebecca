package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralAudio;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralKeyboard;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralMouse;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral.PeripheralScreen;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class HackComputer extends GameList<ProcessorObject> implements Updatable, RenderFree {

	public HackComputer() {
		super(LISTTYPE.LINKED);
		LAYER.PUZZLE.addObject(this);
	}

	////////// PERIPHERAL ////////////

	private boolean keyboard;

	public boolean hasKeyboard() {
		return keyboard;
	}

	private boolean audio;

	public boolean hasAudio() {
		return audio;
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
		resetPeripherals();

		for (ProcessorObject tempProcessor : getList()) {
			if (tempProcessor instanceof PeripheralObject == false)
				continue;

			if (tempProcessor instanceof PeripheralKeyboard)
				keyboard = true;
			
			if (tempProcessor instanceof PeripheralAudio)
				audio = true;

			if (tempProcessor instanceof PeripheralScreen)
				screen = true;

			if (tempProcessor instanceof PeripheralMouse)
				mouse = true;
		}
	}

	private void resetPeripherals() {
		keyboard = false;
		audio = false;
		screen = false;
		mouse = false;
	}

}
