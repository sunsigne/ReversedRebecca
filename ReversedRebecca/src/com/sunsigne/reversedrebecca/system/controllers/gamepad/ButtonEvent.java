package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.util.HashMap;

import net.java.games.input.Component.Identifier;

public class ButtonEvent {

	////////// ENUM ////////////

	public static final int BUTTON_A = 0;

	////////// MAP OR LIST ////////////

	private static HashMap<Identifier, ButtonEvent> map = new HashMap<>();

	public static void buttonAttribution() {
		map.put(Identifier.Button._0, new ButtonEvent(BUTTON_A));
	}

	public static ButtonEvent getButtonEvent(Identifier identifier) {
		return map.get(identifier);
	}

	////////// OBJECT ////////////

	public ButtonEvent(int key) {
		this.key = key;
	}

	private int key;

	public int getKey() {
		return key;
	}

}
