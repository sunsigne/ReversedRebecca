package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class GamepadManager {

	////////// MAP OR LIST ////////////

	private static GameList<GamepadAdapter> list = new GameList<>(LISTTYPE.ARRAY);

	public void addGamepadListener(GamepadAdapter adapter) {
		list.addObject(adapter);
	}

	public void removeGamepadListener(GamepadAdapter adapter) {
		list.removeObject(adapter);
	}

	////////// GAMEPAD ////////////

	private static Controller getGamepad() {

		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

		for (Controller controller : controllers) {
			if (controller.getType() == Controller.Type.GAMEPAD)
				return controller;
		}
		return null;
	}

	////////// TICK ////////////

	public static void tick() {

		Controller gamepad = getGamepad();
		if (gamepad == null)
			return;
 
		// Obtention de la file d'événements de la manette de jeu
		EventQueue eventQueue = gamepad.getEventQueue();
		Event event = new Event();

		gamepad.poll();
		while (eventQueue.getNextEvent(event)) {

			Component comp = event.getComponent();

			for (GamepadAdapter tempAdapter : list.getList()) {

				if (event.getValue() == 1.0f)
					tempAdapter.buttonPressed(ButtonEvent.getButtonEvent(comp.getIdentifier()));
			}

			/*
			  if (event.getValue() != 0) System.out.println(comp.getName());
			  
			  // Vérification que l'événement correspond à l'appui sur le bouton "A" if
			  (comp.getName().equalsIgnoreCase("Bouton 0") && event.getValue() == 1.0f) {
			  // Affichage d'un message dans la console
			  System.out.println("Le bouton A de la manette a été appuyé."); }
			 */
		}

		freeze();
	}

	// Pause pour éviter une utilisation excessive du processeur
	private static void freeze() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
