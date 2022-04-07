package com.sunsigne.reversedrebecca.object.extrabehaviors;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.characteristics.SurVelocity;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.World;

public abstract class SuperExtraBehaviorsObject extends GameObject
		implements SurVelocity, Facing, PathSearcher, Interactive {

	public SuperExtraBehaviorsObject(String name, int x, int y) {
		this(name, x, y, Size.M, Size.M);
	}

	public SuperExtraBehaviorsObject(String name, int x, int y, int w, int h) {
		super(x, y, w, h);
		setName(name);
		if (World.get() != null)
			instructionMap = ("maps/" + World.get().getMapName() + "/" + getName() + ".csv");
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

	////////// SURVELOCICY ////////////

	private int surVelX, surVelY;

	@Override
	public int getSurVelX() {
		return surVelX;
	}

	@Override
	public int getSurVelY() {
		return surVelY;
	}

	@Override
	public void setSurVelX(int surVelX) {
		this.surVelX = surVelX;
	}

	@Override
	public void setSurVelY(int surVelY) {
		this.surVelY = surVelY;
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

	////////// PATH FINDER ////////////

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

	////////// RENDER ////////////

	private boolean invisible;

	public boolean isInvisible() {
		return invisible;
	}

	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}

}
