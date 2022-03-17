package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.World;

public abstract class SuperNPC extends LivingObject implements Interactive {

	private static final int walking_speed = Size.XS / 10;
	private static final int running_speed = Size.XS / 5;

	public SuperNPC(String name, int x, int y) {
		super(name, x, y, walking_speed);
		instructionMap = ("maps/" + World.get().getMapName() + "/" + name + ".csv");
	}

	////////// USEFUL ////////////

	public void setRunning(boolean running) {
		if (running)
			this.speed = running_speed;
		else
			this.speed = walking_speed;
	}

	////////// INSTRUCTION ////////////

	private String instructionMap;

	public String getInstructionMap() {
		return instructionMap;
	}

	////////// INTERACTIVE ////////////

	private boolean isDisabled;

	@Override
	public boolean isDisabled() {
		return isDisabled;
	}

	@Override
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	public void setTripleAction(TripleAction tripleAction) {
		this.tripleAction = tripleAction;
	}

	////////// KEYBOARD ////////////

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		Interactive.super.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		Interactive.super.keyReleased(e);
	}

}
