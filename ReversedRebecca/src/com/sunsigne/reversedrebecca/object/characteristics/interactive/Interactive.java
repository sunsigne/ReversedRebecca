package com.sunsigne.reversedrebecca.object.characteristics.interactive;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.behaviors.CanInteract;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;

public interface Interactive extends Velocity, KeyboardEvent {

	public default void createTextAction() {
		LAYER.WORLD_TEXT.addObject(new TextAction(this, getTripleAction()));
	}

	////////// INTERACTIVE ////////////

	public default String getFile() {
		return "actions.csv";
	}

	public TripleAction getTripleAction();

	public boolean isDisabled();

	public void setDisabled(boolean isDisabled);

	public default boolean canPlayerInterfact() {

		// object is disabled
		if (isDisabled())
			return false;

		Player player = new PlayerFinder().getPlayer();

		// player is null
		if (player == null)
			return false;

		// player specifically can't interact
		if (!player.getBehaviorList().containsObject(new CanInteract(player)))
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

	////////// KEYBOARD ////////////

	@Override
	public default void keyPressed(KeyEvent e) {

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
	public default void keyReleased(KeyEvent e) {

	}

}
