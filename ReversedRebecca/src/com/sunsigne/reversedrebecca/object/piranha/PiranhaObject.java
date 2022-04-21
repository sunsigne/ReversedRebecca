package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.world.World;

public abstract class PiranhaObject extends GameObject
		implements Facing, SpeedVariator, PathSearcher, Interactive, CollisionReactor {

	public PiranhaObject(String name, int x, int y) {
		super(x, y);
		this.name = name.toLowerCase();

		if (World.get() != null)
			piranhaFile = ("maps/" + World.get().getMapName() + "/" + getName() + ".csv");
	}

	////////// NAME ////////////

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name.toLowerCase();
	}

	////////// PIRANHA ////////////

	private String piranhaFile;

	public String getPiranhaFile() {
		return piranhaFile;
	}

	////////// FACING ////////////

	private DIRECTION facing = DIRECTION.DOWN;

	@Override
	public DIRECTION getFacing() {
		return facing;
	}

	@Override
	public void setFacing(DIRECTION facing) {
		this.facing = facing;
	}

	////////// SPEED ////////////

	private SPEEDNESS speedness = SPEEDNESS.NORMAL;

	@Override
	public SPEEDNESS getSpeedness() {
		return speedness;
	}

	@Override
	public void setSpeedness(SPEEDNESS speedness) {
		this.speedness = speedness;
	}

	////////// PATH FINDER ////////////

	private boolean moveTowardGoal = true;

	public boolean mustFollowPath() {
		return moveTowardGoal;
	}

	public void setFollowPath(boolean moveTowardGoal) {
		this.moveTowardGoal = moveTowardGoal;
	}

	private Position goal;

	@Override
	public void setGoal(Position goal) {
		this.goal = goal;
	}

	@Override
	public Position getGoal() {
		return goal;
	}

	private DIRECTION path;

	@Override
	public void setPath(DIRECTION path) {
		this.path = path;
	}

	@Override
	public DIRECTION getPath() {
		return path;
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

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Interactive.super.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Interactive.super.keyReleased(e);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return true;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}