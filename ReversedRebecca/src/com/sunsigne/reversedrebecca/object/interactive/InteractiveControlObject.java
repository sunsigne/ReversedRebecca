package com.sunsigne.reversedrebecca.object.interactive;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.CannotInteract;
import com.sunsigne.reversedrebecca.pattern.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;

public abstract class InteractiveControlObject extends GameObject implements KeyboardEvent, Translatable {

	public InteractiveControlObject(int x, int y) {
		super(x, y);
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

	private boolean isPlayerNearby(int diffX, int diffY) {
		float playerDistance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
		float maxDistance = Size.M + Size.XS;

		return playerDistance < maxDistance;
	}

	private boolean canPlayerInterfact() {

		// object is disabled
		if (isDisabled())
			return false;

		Player player = new PlayerFinder().getPlayer();

		// player is null
		if (player == null)
			return false;

		// player specifically can't interact
		if (player.getBehaviorList().cointainsObject(new CannotInteract(player)))
			return false;

		// player is not on the same layer
		if (player.getHandler() != getHandler())
			return false;

		int diffX = getX() - player.getX();
		int diffY = getY() - player.getY();

		// player is too far
		if (!isPlayerNearby(diffX, diffY))
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

	////////// RENDER ////////////

	public void drawTripleActionText(Graphics g) {
		if (!canPlayerInterfact())
			return;

		Player player = new PlayerFinder().getPlayer();

		if (getTripleAction().getAction(0) == null)
			return;

		// if ONE action, draw it in front of the player
		if (getTripleAction().getAction(1) == null) {
			drawFacingActionText(g, player.getFacing(), getTripleAction().getAction(0));
			return;
		}

		// if TWO actions, draw them perpendicularly to the player
		if (player.getFacing() == DIRECTION.UP || player.getFacing() == DIRECTION.DOWN) {
			drawFacingActionText(g, DIRECTION.LEFT, getTripleAction().getAction(0));
			drawFacingActionText(g, DIRECTION.RIGHT, getTripleAction().getAction(1));
		} else {
			drawFacingActionText(g, DIRECTION.UP, getTripleAction().getAction(0));
			drawFacingActionText(g, DIRECTION.DOWN, getTripleAction().getAction(1));
		}

		// if THREE actions, draw the third in front of the player
		if (getTripleAction().getAction(2) != null)
			drawFacingActionText(g, player.getFacing(), getTripleAction().getAction(2));

	}

	private void drawFacingActionText(Graphics g, DIRECTION facing, Action action) {

		Font font = new Font("arial", 1, 25);
		String text = "[" + KeyEvent.getKeyText(action.getKeyEvent()) + "]" + " " + action.getName().toUpperCase();

		int[] rect;

		switch (facing) {
		case LEFT:
			rect = new int[] { getX() - (int)(1.5*Size.M), getY(), getWidth(), getHeight() };
			break;
		case RIGHT:
			rect = new int[] { getX() + (int)(1.5*Size.M), getY(), getWidth(), getHeight() };
			break;
		case UP:
			rect = new int[] { getX(), getY() - Size.M, getWidth(), getHeight() };
			break;
		case DOWN:
			rect = new int[] { getX(), getY() + Size.M, getWidth(), getHeight() };
			break;
		default:
			rect = getRect();
			break;
		}

		new TextDecoration().drawOutlinesString(g, text, font, rect);
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
				return;

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
