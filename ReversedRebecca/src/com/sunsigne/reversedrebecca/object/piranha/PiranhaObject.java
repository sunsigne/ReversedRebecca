package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.characteristics.Stunnable;
import com.sunsigne.reversedrebecca.object.characteristics.Waitfor;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.world.World;

public abstract class PiranhaObject extends GameObject
		implements Waitfor, Facing, Stunnable, SpeedVariator, PathSearcher, Interactive, CollisionReactor {

	// the only difference between PiranhaObject and LivingObject is that
	// PiranhaObject are not supposed to move by themself.
	// That's it. A homing rolling pin is then a "LivingObject".
	public PiranhaObject(String name, int x, int y) {
		super(x, y);
		this.name = name.toLowerCase();
		setBlockingPath(true);

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

	////////// WAITFOR ////////////

	private ConditionalListener waitfor;

	@Override
	public ConditionalListener getWaitfor() {
		return waitfor;
	}

	@Override
	public void setWaitfor(ConditionalListener listener) {
		if (listener == null)
			setStunned(false);
		this.waitfor = listener;
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

	////////// STUNNABLE ////////////

	private boolean stunned;

	@Override
	public boolean isStunned() {
		return stunned;
	}

	@Override
	public void setStunned(boolean stunned) {
		setMotionless();
		this.stunned = stunned;
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

	private boolean blockingPath;

	@Override
	public boolean isBlockingPath() {
		return blockingPath;
	}

	public void setBlockingPath(boolean blockingPath) {
		this.blockingPath = blockingPath;
	}

	private int hitboxX, hitboxY;
	private int hitboxW = getWidth();
	private int hitboxH = getHeight();

	// width and height should be between 0 and 16
	public void setBounds(int x, int y, int width, int height) {
		this.hitboxX = x * 6;
		this.hitboxY = y * 6;
		this.hitboxW = width * 6;
		this.hitboxH = height * 6;
	}

	@Override
	public Rectangle getBounds() {
		int x = getX() + hitboxX;
		int y = getY() + hitboxY;
		int w = hitboxW;
		int h = hitboxH;
		return new Rectangle(x, y, w, h);
	}

}
