package com.sunsigne.reversedrebecca.object.piranha.living.player;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.ressources.FileTask;

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

		return clazz + " : " + dirName + " / " + dirCanInterract + " / " + dirPos + " / " + "GOAL:"+ dirGoal;
	}
	
	////////// SPEED ////////////

	private void updateSpeed() {
		if (isPathNull())
			setSpeedness(SPEEDNESS.PLAYER_SPEED);
	}

	////////// HP ////////////

	private void createCharacteristic(String text, int num) {
		String content = new FileTask().read(userData, file);
		String new_content = text + "=3" + System.getProperty("line.separator") + content;
		new FileTask().write(file, new_content);
	}

	private void loadHealth() {
		String txtMaxHp = new FileTask().read(userData, "MaxHp", file);

		// if the file "characteristics" has no value for the hp, create one
		if (txtMaxHp.isEmpty()) {
			createCharacteristic(System.getProperty("line.separator") + "MaxHp", 3);
			txtMaxHp = "3";
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

	public boolean canInterract() {
		if (isStunned() == false)
			return canInterract;

		if (getCondition() == CONDITION.KO)
			return false;

		return canInterract;
	}

	public void setCanInterract(boolean canInterract) {
		this.canInterract = canInterract;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (isUserAllowedToMovePlayer())
			UserKeyMovePlayer.getInstance().movePlayerByKey(this);

		super.tick();

		updateSpeed();
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
		int key = e.getKeyCode();
		UserKeyMovePlayer.getInstance().directionKey(this, key, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		UserKeyMovePlayer.getInstance().directionKey(this, key, false);
	}

	////////// COLLISION ////////////

	@Override
	protected void defaultCollindingReaction(CollisionDetector detectorObject) {

	}

}
