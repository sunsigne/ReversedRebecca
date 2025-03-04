package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePreseting;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class GamepadManager {

	////////// MAP OR LIST ////////////

	private static GameList<GamepadAdapter> list = new GameList<>(LISTTYPE.LINKED);

	public static GameList<GamepadAdapter> getList() {
		return list;
	}

	public void addGamepadListener(GamepadAdapter adapter) {
		list.addObject(adapter);
	}

	public void removeGamepadListener(GamepadAdapter adapter) {
		list.removeObject(adapter);
	}

	////////// GAMEPAD ////////////

	private static Controller registeredControllers;
	protected static Controller currentControllers;

	private static Controller getGamepad() {
		if (currentControllers != null)
			return registeredControllers;

		Controller[] controllers = GamepadUpdate.getRegisteredGamepad();

		for (Controller controller : controllers) {
			if (controller.getType() == Controller.Type.GAMEPAD || controller.getType() == Controller.Type.STICK)
				return getRegisteredGamepad(controller);
		}
		return null;
	}

	private static Controller getRegisteredGamepad(Controller controller) {
		if (registeredControllers == null || registeredControllers.getType() != controller.getType())
			registeredControllers = controller;

		currentControllers = controller;
		return registeredControllers;
	}

	////////// TICK ////////////

	private static boolean usingGamepad;

	private static boolean updatingGamepad(Controller gamepad) {
		if (gamepad == null) {
			if (usingGamepad) {
				usingGamepad = false;
				ControllerManager.getInstance().setUsingGamepad(false);
			}
			return true;
		}

		else {
			if (usingGamepad == false && gamepad.poll()) {
				usingGamepad = true;
				ControllerManager.getInstance().setUsingGamepad(true);
				resetMousePresetings();
			}
			return false;
		}
	}

	private static void resetMousePresetings() {
		for (GamepadAdapter tempAdapter : list.getList()) {

			if (tempAdapter instanceof GamepadController == false)
				continue;
			var tempEvent = ((GamepadController) tempAdapter).getGamepadEvent();

			if (tempEvent instanceof MousePreseting == false)
				continue;
			var tempPreset = (MousePreseting) tempEvent;

			tempPreset.setPreset(tempPreset.getDefaultPreset());
		}
	}

	public static void tick() {

		Controller gamepad = getGamepad();
		if (updatingGamepad(gamepad))
			return;

		// Obtention de la file d'événements de la manette de jeu
		EventQueue eventQueue = gamepad.getEventQueue();
		Event event = new Event();

		if (gamepad.poll() == false)
			registeredControllers = null;

		while (eventQueue.getNextEvent(event)) {

			Component comp = event.getComponent();
			if (comp.getIdentifier() == Identifier.Axis.UNKNOWN || comp.getIdentifier() == Identifier.Axis.Z)
				continue;

			ButtonEvent button = ButtonEvent.getButtonEvent(gamepad.getType(), comp.getIdentifier(), event.getValue());

			if (button == null || button.getKey() == ButtonEvent.ERROR)
				continue;

			int pressedButton = ButtonEvent.ERROR;
			int releasedButton = ButtonEvent.ERROR;

			GameList<GenericListener> adapter_event_list = new GameList<>(LISTTYPE.ARRAY);
			
			var clone = new ListCloner().deepClone(list);
			for (GamepadAdapter tempAdapter : clone.getList()) {

				if (event.getValue() < -0.5f || event.getValue() > 0.5f)
					if (tempAdapter.isPressed(button.getKey()) == false) {
						pressedButton = button.getKey();
						GenericListener listener = () -> tempAdapter.buttonPressed(button);
						adapter_event_list.addObject(listener);
					}

				if (event.getValue() > -0.5f && event.getValue() < 0.5f) {
					if (tempAdapter.isPressed(button.getKey())) {
						releasedButton = button.getKey();
						GenericListener listener = () -> tempAdapter.buttonReleased(button);
						adapter_event_list.addObject(listener);
					}
				}

			}

			for (GenericListener tempListener : adapter_event_list.getList())
				tempListener.doAction();

			updatePressedButton(pressedButton);
			updateReleasedButton(releasedButton);
		}
	}

	private static void updatePressedButton(int pressedButton) {
		if (pressedButton == ButtonEvent.ERROR)
			return;

		GamepadController.pressing.addObject(pressedButton);

		if (pressedButton == ButtonEvent.LEFT || pressedButton == ButtonEvent.RIGHT)
			GamepadController.pressing.addObject(ButtonEvent.NULL_X);
		if (pressedButton == ButtonEvent.UP || pressedButton == ButtonEvent.DOWN)
			GamepadController.pressing.addObject(ButtonEvent.NULL_Y);
	}

	private static void updateReleasedButton(int releasedButton) {
		if (releasedButton == ButtonEvent.ERROR)
			return;

		// casting an Integer object so as not to confuse Java as calling function
		// "remove(int index)" instead of "remove(Object o)"
		GamepadController.pressing.removeObject(Integer.valueOf(releasedButton));

		if (releasedButton == ButtonEvent.NULL_X) {
			GamepadController.pressing.removeObject(Integer.valueOf(ButtonEvent.LEFT));
			GamepadController.pressing.removeObject(Integer.valueOf(ButtonEvent.RIGHT));
		}
		if (releasedButton == ButtonEvent.NULL_Y) {
			GamepadController.pressing.removeObject(Integer.valueOf(ButtonEvent.UP));
			GamepadController.pressing.removeObject(Integer.valueOf(ButtonEvent.DOWN));
		}
	}

}
