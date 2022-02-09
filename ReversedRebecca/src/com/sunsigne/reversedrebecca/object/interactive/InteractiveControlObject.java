package com.sunsigne.reversedrebecca.object.interactive;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.CanInteract;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;

public abstract class InteractiveControlObject extends GameObject implements Difficulty, KeyboardEvent {

	protected String file = "actions.csv";
	
	public InteractiveControlObject(int x, int y) {
		this(LVL.NULL, x, y);
	}

	public InteractiveControlObject(LVL difficulty, int x, int y) {
		super(x, y);
		this.difficulty = difficulty;
	}

	////////// DIFFICULTY ////////////

	private LVL difficulty;

	@Override
	public LVL getDifficulty() {
		return difficulty;
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		this.difficulty = difficulty;
	}

	////////// INTERACTION ////////////

	public abstract TripleAction getTripleAction();

	private boolean isDisabled;

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	protected boolean canPlayerInterfact() {

		// object is disabled
		if (isDisabled())
			return false;

		Player player = new PlayerFinder().getPlayer();

		// player is null
		if (player == null)
			return false;

		// player specifically can't interact
		if (!player.getBehaviorList().cointainsObject(new CanInteract(player)))
			return false;

		// player is not on the same layer
		if (player.getHandler() != getHandler())
			return false;

		int diffX = getX() - player.getX();
		int diffY = getY() - player.getY();

		// player is too far
		if (new PlayerFinder().isPlayerFutherThan(this, Size.M + Size.XS))
			return false;

		// player is watching the object
		switch (player.getFacing()) {

		case LEFT:
			// player is at the right of the object & the object is almost on the same Y
			if (diffX < 0 && Math.pow(diffX, 2) > Math.pow(diffY, 2))
				return true;
			else
				return false;

		case RIGHT:
			// player is at the left of the object & the object is almost on the same Y
			if (diffX > 0 && Math.pow(diffX, 2) > Math.pow(diffY, 2))
				return true;
			else
				return false;

		case UP:
			// player is under the object & the object is almost on the same X
			if (diffY < 0 && Math.pow(diffX, 2) < Math.pow(diffY, 2))
				return true;
			else
				return false;

		case DOWN:
			// player is above the object & the object is almost on the same X
			if (diffY > 0 && Math.pow(diffX, 2) < Math.pow(diffY, 2))
				return true;
			else
				return false;

		default:
			return false;
		}
	}

	////////// TICK ////////////

	private boolean flag;

	@Override
	public void tick() {
		createTextAction();
	}

	protected void createTextAction() {
		if (flag)
			return;

		if (getHandler() == null)
			return;

		LAYER.WORLD_TEXT.addObject(new TextAction(this, getTripleAction()));
		flag = true;
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (!canPlayerInterfact())
			return;

		if (getTripleAction() == null)
			return;

		Action tempAction;
		int key = e.getKeyCode();

		for (int index = 0; index < 3; index++) {

			tempAction = getTripleAction().getAction(index);

			if (tempAction == null)
				continue;

			if (key == tempAction.getKeyEvent()) {
				tempAction.doAction();
				return;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
