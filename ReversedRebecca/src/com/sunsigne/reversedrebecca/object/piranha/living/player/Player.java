package com.sunsigne.reversedrebecca.object.piranha.living.player;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;

public class Player extends LivingObject {

	private String file = "characteristics.csv";
	private boolean userData = true;

	public Player(int x, int y) {
		super("PLAYER", x, y);
		UserKeyMovePlayer.refreshInstance();
		setDisabled(true);
		setUserAllowedToMovePlayer(true);
		setCanInterract(true);

		loadHealth();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PIRANHA : PLAYER";

		var dirName = "NAME:" + getName().toUpperCase();
		var dirCanInterract = "CAN INTERACT:" + canInterract();

		GoalObject goal = null;
		goal = new GoalObject(getX(), getY(), true);
		var dirPos = "POS:" + goal.getX() + "-" + goal.getY();

		goal = null;
		if (getGoal() != null)
			goal = new GoalObject(getGoal().getX(), getGoal().getY(), true);
		var dirGoal = goal == null ? "N/A" : goal.getX() + "-" + goal.getY();

		return clazz + " : " + dirName + " / " + dirCanInterract + " / " + dirPos + " / " + "GOAL:" + dirGoal;
	}

	////////// SPEED ////////////

	private boolean forcePushed;

	public boolean isForcePushed() {
		return forcePushed;
	}

	public void setForcePushed(boolean forcePushed) {
		this.forcePushed = forcePushed;
	}

	private void updateSpeed() {
		if (isPathNull())
			setSpeedness(SPEEDNESS.PLAYER_SPEED);
	}

	////////// HP ////////////

	private void createCharacteristic(String text, int num) {
		String content = new FileTask().read(userData, file);
		String new_content = text + "=5" + System.getProperty("line.separator") + content;
		new FileTask().write(file, new_content);
	}

	private void loadHealth() {
		String txtMaxHp = new FileTask().read(userData, "MaxHp", file);

		// if the file "characteristics" has no value for the hp, create one
		if (txtMaxHp.isEmpty()) {
			createCharacteristic(System.getProperty("line.separator") + "MaxHp", 3);
			txtMaxHp = "5";
		}

		setMaxHp(Integer.parseInt(txtMaxHp));
		setFullHp();
	}

	////////// PATH FINDER ////////////

	private boolean isPathNull() {
		if (Cutscene.isRunning() == false)
			return true;
		if (getPath() == null)
			return true;
		else
			return false;
	}

	@Override
	public boolean mustFollowPath() {
		if (isStunned())
			return false;

		if (isPathNull())
			return false;

		else
			return true;
	}

	////////// INTERACTIVE ////////////

	private boolean canInterract;
	private boolean fakingCanInterract;

	public boolean getFakingCanInterract() {
		return canInterract() || fakingCanInterract;
	}

	public boolean canInterract() {
		if (isStunned() == false)
			return canInterract;

		if (getCondition() == CONDITION.KO)
			return false;

		return canInterract;
	}

	public void setCanInterract(boolean canInterract) {
		setCanInterract(canInterract, false);
	}

	public void setCanInterract(boolean canInterract, boolean fakingCanInterract) {
		this.canInterract = canInterract;
		this.fakingCanInterract = fakingCanInterract;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (isUserAllowedToMovePlayer())
			UserKeyMovePlayer.getInstance().movePlayerByKey(this);

		super.tick();

		updateSpeed();
		setForcePushed(false);
	}

	////////// KEYBOARD ////////////

	private boolean isUserAllowedToMovePlayer;

	private boolean isUserAllowedToMovePlayer() {
		if (!isPathNull())
			return false;

		if (isStunned())
			return false;

		else
			return isUserAllowedToMovePlayer;
	}

	public void setUserAllowedToMovePlayer(boolean isUserAllowedToMovePlayer) {
		setMotionless();
		this.isUserAllowedToMovePlayer = isUserAllowedToMovePlayer;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		UserKeyMovePlayer.getInstance().directionKey(this, e.getKeyCode(), -1, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		UserKeyMovePlayer.getInstance().directionKey(this, e.getKeyCode(), -1, false);
	}
	
	////////// GAMEPAD ////////////
	
	private GamepadController gamepadController = new GamepadController(this);
	
	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}
	
	@Override
	public void buttonPressed(ButtonEvent e) {
		UserKeyMovePlayer.getInstance().directionKey(this, 65535, e.getKey(), true);
	}
	
	@Override
	public void buttonReleased(ButtonEvent e) {
		UserKeyMovePlayer.getInstance().directionKey(this, 65535, e.getKey(), false);
	}

	////////// COLLISION ////////////

	@Override
	protected void defaultCollindingReaction(CollisionDetector detectorObject) {

	}

}
