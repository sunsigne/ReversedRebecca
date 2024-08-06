package com.sunsigne.reversedrebecca.object.characteristics.interactive;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.Highlightable;
import com.sunsigne.reversedrebecca.object.piranha.ChoiceObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.natural.independant.SingleInteractivityLaw;
import com.sunsigne.reversedrebecca.piranha.actions.action.TalkAction;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.world.controllers.UserCanInputRestartDialogue;

public interface Interactive extends Highlightable, KeyboardEvent, GamepadEvent {

	public default void createTextAction() {
		if (getTripleAction() == null)
			return;

		LAYER.WORLD_TEXT.addObject(new TextAction(this, getTripleAction()));
		LAYER.WORLD_TEXT.addObject(getTripleAction().getRequirementBubble());
	}

	////////// INTERACTIVE ////////////

	public TripleAction getTripleAction();

	public boolean isDisabled();

	public void setDisabled(boolean isDisabled);

	public default boolean canPlayerInteract() {
		return canPlayerInteract(false);
	}

	public default boolean canPlayerInteract(boolean checkFakeInteract) {

		// player is already interacting with an object
		if (this instanceof ChoiceObject == false) {
			if (SingleInteractivityLaw.getCurrentInteractor() != null
					&& SingleInteractivityLaw.getCurrentInteractor() != this)
				return false;
		}

		// object is disabled
		if (isDisabled())
			return false;

		Player player = new PlayerFinder().getPlayer();

		// player is null
		if (player == null)
			return false;

		// player specifically can't interact
		if (player.canInteract() == false) {
			if (!checkFakeInteract || player.getFakingCanInterract() == false)
				return false;
		}

		// player is not on the same layer
		if (player.getHandler() != getHandler())
			return false;

		int diffX = getX() - player.getX();
		int diffY = getY() - player.getY();

		// player is too far
		if (new PlayerFinder().isPlayerFutherThan(this, 1))
			return false;

		// player is superimposed on the object
		if (new PlayerFinder().isPlayerCloserThan(this, 1))
			return true;

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

	public GameTimer getDelayer();

	public void setDelayer(GameTimer delayer);

	// besure the highlight for large objects does not "blink" when the player change of tile
	public default boolean canPlayerInteractDelayer(boolean checkFakeInteract) {
		if (canPlayerInteract(checkFakeInteract)) {
			if (getDelayer() != null)
				getDelayer().destroy();

			setDelayer(new GameTimer(2, true, () -> {
			}));
		}

		if (getDelayer() != null && getDelayer().isReady())
			setDelayer(null);

		return getDelayer() != null;
	}

	////////// HIGHLIGHT ////////////

	@Override
	default boolean getHighlightCondition() {
		if (canPlayerInteractDelayer(false) == false)
			return false;

		if (getTripleAction() == null || getTripleAction().cannotDoAnyAction())
			return false;

		return true;
	}

	////////// KEYBOARD ////////////

	@Override
	public default void keyPressed(KeyEvent e) {
		inputPressed(e.getKeyCode(), -1);
	}

	@Override
	public default void keyReleased(KeyEvent e) {

	}

	////////// GAMEPAD ////////////

	@Override
	public default void buttonPressed(ButtonEvent e) {
		inputPressed(65535, e.getKey());
	}

	@Override
	public default void buttonReleased(ButtonEvent e) {

	}

	////////// INPUT ////////////

	public default void inputPressed(int key, int button) {

		if (!canPlayerInteract())
			return;

		if (getTripleAction() == null)
			return;

		Action tempAction;

		for (int index = 0; index < 3; index++) {

			tempAction = getTripleAction().getAction(index);

			if (tempAction == null)
				continue;

			if (key == tempAction.getKeyEvent() || button == tempAction.getButtonEvent()) {
				if (tempAction instanceof TalkAction)
					UserCanInputRestartDialogue.lastChat = tempAction;
				tempAction.doAction();
				return;
			}
		}
	}

}
