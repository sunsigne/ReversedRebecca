package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.util.HashMap;

import net.java.games.input.Component.Identifier;

public class ButtonEvent {

	////////// MAP OR LIST ////////////

	private static HashMap<Identifier, ButtonEvent> map = new HashMap<>();

	public static final int BUTTON_A            = 0;
	public static final int BUTTON_B            = 1;
	public static final int BUTTON_X            = 2;
	public static final int BUTTON_Y            = 3;	
	public static final int BUTTON_L1           = 4;	
	public static final int BUTTON_R1           = 5;	
	public static final int BUTTON_SELECT       = 6;	
	public static final int BUTTON_START        = 7;	
	public static final int BUTTON_LEFT_ANALOG  = 8;	
	public static final int BUTTON_RIGHT_ANALOG = 9;
	
	public static void buttonAttribution() {
		map.put(Identifier.Button._0, new ButtonEvent(BUTTON_A));
		map.put(Identifier.Button._1, new ButtonEvent(BUTTON_B));
		map.put(Identifier.Button._2, new ButtonEvent(BUTTON_X));
		map.put(Identifier.Button._3, new ButtonEvent(BUTTON_Y));		
		map.put(Identifier.Button._4, new ButtonEvent(BUTTON_L1));
		map.put(Identifier.Button._5, new ButtonEvent(BUTTON_R1));
		map.put(Identifier.Button._6, new ButtonEvent(BUTTON_SELECT));
		map.put(Identifier.Button._7, new ButtonEvent(BUTTON_START));
		map.put(Identifier.Button._8, new ButtonEvent(BUTTON_LEFT_ANALOG));
		map.put(Identifier.Button._9, new ButtonEvent(BUTTON_RIGHT_ANALOG));
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
