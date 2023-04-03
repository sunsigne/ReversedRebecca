package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class GamepadManager {

	////////// MAP OR LIST ////////////

	private static GameList<GamepadAdapter> list = new GameList<>(LISTTYPE.ARRAY);

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

	private static int timer = 0;

	private static boolean mustRefreshController() {
		timer++;
		if (timer < Game.SEC * 2)
			return false;
		timer = 0;
		return true;
	}

	private static void refreshController() {
		if (mustRefreshController() == false)
			return;

		var clazz = ControllerEnvironment.getDefaultEnvironment();
		Field controller = null;
		Field names = null;

		System.err.println("reset of timer");

		try {
			controller = clazz.getClass().getDeclaredField("controllers");
			controller.setAccessible(true);
			controller.set(clazz, null);

			names = clazz.getClass().getDeclaredField("loadedPluginNames");
			names.setAccessible(true);
			names.set(clazz, new ArrayList<>());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Controller getGamepad() {

		refreshController();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

		for (Controller controller : controllers) {
			if (controller.getType() == Controller.Type.GAMEPAD || controller.getType() == Controller.Type.STICK)
				return controller;
		}
		return null;
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
			if (usingGamepad == false) {
				usingGamepad = true;
				ControllerManager.getInstance().setUsingGamepad(true);
			}
			return false;
		}
	}

	public static void tick() {

		Controller gamepad = getGamepad();
		if (updatingGamepad(gamepad))
			return;

		// Obtention de la file d'événements de la manette de jeu
		EventQueue eventQueue = gamepad.getEventQueue();
		Event event = new Event();

		gamepad.poll();
		while (eventQueue.getNextEvent(event)) {

			Component comp = event.getComponent();
			if (comp.getIdentifier() == Identifier.Axis.UNKNOWN)
				continue;

			for (GamepadAdapter tempAdapter : list.getList()) {

				ButtonEvent button = ButtonEvent.getButtonEvent(gamepad.getType(), comp.getIdentifier(),
						event.getValue());

				if (event.getValue() < -0.05f || event.getValue() > 0.05f)
					tempAdapter.buttonPressed(button);

				if (event.getValue() > -0.05f && event.getValue() < 0.05f)
					tempAdapter.buttonReleased(button);
			}
		}
	}

}
