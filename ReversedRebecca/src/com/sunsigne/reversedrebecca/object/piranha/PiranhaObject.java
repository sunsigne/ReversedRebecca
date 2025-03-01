package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.Highlightable;
import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.characteristics.Stunnable;
import com.sunsigne.reversedrebecca.object.characteristics.Waitfor;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.RegistrableInteractive;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.piranha.Piranha;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.world.World;

public abstract class PiranhaObject extends CustomHitboxObject
		implements Waitfor, Facing, Stunnable, SpeedVariator, PathSearcher, RegistrableInteractive {

	// the only difference between PiranhaObject and LivingObject is that
	// PiranhaObject are not supposed to move by themself.
	// That's it. A homing rolling pin is then a "LivingObject".
	public PiranhaObject(String name, int x, int y) {
		super(x, y, 0, 0, 0);
		this.name = name.toLowerCase();
		this.textureName = name.toLowerCase();
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
		if (name == null)
			return;
		
		this.name = name.toLowerCase();
		setTextureName(name);
	}

	private String textureName;

	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		if (textureName != null)
			this.textureName = textureName.toLowerCase();
	}

	@Override
	public String toString() {
		var clazz = "PIRANHA";

		var dirName = "NAME:" + getName().toUpperCase();
		var dirTextureName = "TEXTURE NAME:" + getTextureName().toUpperCase();
		var goal = new GoalObject(getX(), getY(), true);
		var dirPos = "POS:" + goal.getX() + "-" + goal.getY();
		var dirBlockPath = "BLOCKPATH:" + isBlockingPath();
		var result = clazz + " : " + dirName + " / " + dirTextureName + " / " + dirPos + " / " + dirBlockPath;

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

	private GameList<Highlightable> spreadInteractivenessList = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<Highlightable> getSpreadInteractivenessList() {
		return spreadInteractivenessList;
	}

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

	private GameTimer delayer;

	@Override
	public GameTimer getDelayer() {
		return delayer;
	}

	@Override
	public void setDelayer(GameTimer delayer) {
		this.delayer = delayer;
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

	private boolean forceHighlight;

	public void setForceHighlight(boolean forceHighlight) {
		this.forceHighlight = forceHighlight;
	}

	@Override
	public boolean getHighlightCondition() {
		if (forceHighlight)
			return true;
		else
			return RegistrableInteractive.super.getHighlightCondition();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PIRANHA;
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

}
