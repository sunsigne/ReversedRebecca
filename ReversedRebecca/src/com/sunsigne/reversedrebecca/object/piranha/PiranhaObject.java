package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.characteristics.Stunnable;
import com.sunsigne.reversedrebecca.object.characteristics.Waitfor;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.RegistrableInteractive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.piranha.Piranha;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.world.World;

public abstract class PiranhaObject extends GameObject
		implements Waitfor, Facing, Stunnable, SpeedVariator, PathSearcher, RegistrableInteractive, CollisionReactor {

	// the only difference between PiranhaObject and LivingObject is that
	// PiranhaObject are not supposed to move by themself.
	// That's it. A homing rolling pin is then a "LivingObject".
	public PiranhaObject(String name, int x, int y) {
		super(x, y);
		this.name = name.toLowerCase();
		set_stun_at_waitfor_attribution(true);
		setHighlightAbovePlayer(true);
		setBlockingPath(true);

		if (World.get() != null)
			piranhaFile = ("maps/" + World.get().getMapName() + "/" + getName() + ".csv");

		optimize();
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

	@Override
	public String toString() {
		var clazz = "PIRANHA";

		var dirName = "NAME:" + getName().toUpperCase();
		var goal = new GoalObject(getX(), getY(), true);
		var dirPos = "POS:" + goal.getX() + "-" + goal.getY();
		var dirBlockPath = "BLOCKPATH:" + isBlockingPath();
		var result = clazz + " : " + dirName + " / " + dirPos + " / " + dirBlockPath;

		if (getTripleAction() != null) {
			if (getTripleAction().getAction(0) != null)
				result = result
						.concat(" / ACTION 1:" + getTripleAction().getAction(0).getDisplayedText().toUpperCase());
			if (getTripleAction().getAction(1) != null)
				result = result
						.concat(" / ACTION 2:" + getTripleAction().getAction(1).getDisplayedText().toUpperCase());
			if (getTripleAction().getAction(2) != null)
				result = result
						.concat(" / ACTION 3:" + getTripleAction().getAction(2).getDisplayedText().toUpperCase());
		}

		return result;
	}

	////////// WAITFOR ////////////

	private ConditionalListener waitfor;
	private boolean stun_at_waitfor_attribution;

	public boolean does_stun_at_waitfor_attribution() {
		return stun_at_waitfor_attribution;
	}

	public void set_stun_at_waitfor_attribution(boolean stun_at_waitfor_attribution) {
		this.stun_at_waitfor_attribution = stun_at_waitfor_attribution;
	}

	@Override
	public ConditionalListener getWaitfor() {
		return waitfor;
	}

	@Override
	public void setWaitfor(ConditionalListener listener) {
		if (listener == null && does_stun_at_waitfor_attribution())
			setStunned(false);
		this.waitfor = listener;
	}

	////////// PIRANHA ////////////

	private String piranhaFile;

	public String getPiranhaFile() {
		return piranhaFile;
	}

	public void optimize() {
		if (this instanceof Player)
			return;

		String content = new Piranha().getContent(this);

		if (content == null)
			return;

		if (content.contains("ACTION"))
			return;

		destroyControls();
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

	private boolean pathFinderDisabled;

	@Override
	public boolean isPathFinderDisabled() {
		return pathFinderDisabled;
	}

	@Override
	public void enablePathFinder() {
		pathFinderDisabled = false;
	}

	@Override
	public void disabledPathFinder() {
		pathFinderDisabled = true;
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

	private TripleAction registeredTripleAction;

	@Override
	public TripleAction getRegisteredTripleAction() {
		return registeredTripleAction;
	}

	public void setRegisteredTripleAction(TripleAction tripleAction) {
		this.registeredTripleAction = tripleAction;
	}

	////////// HIGHLIGHT ////////////

	private boolean hightligh_above_player;

	@Override
	public boolean isHighlightAbovePlayer() {
		return hightligh_above_player;
	}

	public void setHighlightAbovePlayer(boolean hightligh_above_player) {
		this.hightligh_above_player = hightligh_above_player;
	}
	
	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		RegistrableInteractive.super.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		RegistrableInteractive.super.keyReleased(e);
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		RegistrableInteractive.super.buttonPressed(e);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		RegistrableInteractive.super.buttonReleased(e);
	}

	////////// COLLISION ////////////

	private boolean blockingSight;

	@Override
	public boolean isBlockingSight() {
		return blockingSight;
	}

	public void setBlockingSight(boolean blockingSight) {
		this.blockingSight = blockingSight;
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
		int pixel = 16;
		int ratio = Size.M / pixel;
		this.hitboxX = x * ratio;
		this.hitboxY = y * ratio;
		this.hitboxW = width * ratio;
		this.hitboxH = height * ratio;
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
