package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;
import java.awt.event.MouseListener;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadAdapter;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class RefreshProcessorLaw extends IndependantLaw {

	////////// INDEPENDANT LAW ////////////

	private static IndependantLaw independantLaw = new RefreshProcessorLaw();

	@Override
	public IndependantLaw getIndependantLaw() {
		return independantLaw;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof ProcessorObject == false)
			return;

		ProcessorObject processor = (ProcessorObject) object;

		updateMouseListener(processor);
		updateGamepadListener(processor);
	}

	private void updateMouseListener(ProcessorObject processor) {		
		// if existing processor is already clickable, return void
		for (MouseListener tempListener : Game.getInstance().getMouseListeners()) {
			if (tempListener == processor.getMouseController())
				return;
		}

		// else, besure it is clickable
		Game.getInstance().addMouseListener(processor.getMouseController());		
	}
	
	private void updateGamepadListener(ProcessorObject processor) {		
		// same thing as above, but for gamepad
		for (GamepadAdapter tempListener : Game.getInstance().getGamepadListeners()) {
			if (tempListener == processor.getGamepadController())
				return;
		}

		Game.getInstance().addGamepadListener(processor.getGamepadController());		
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
