package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.util.HashMap;

import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller.Type;

public class ButtonEvent {

	public static final int ERROR = -1;

	public static final int A = 0;
	public static final int B = 1;
	public static final int X = 2;
	public static final int Y = 3;
	public static final int L1 = 4;
	public static final int R1 = 5;
	public static final int SELECT = 6;
	public static final int START = 7;
	public static final int LSTICK = 8;
	public static final int RSTICK = 9;

	public static final int LEFT = 10;
	public static final int RIGHT = 11;
	public static final int UP = 12;
	public static final int DOWN = 13;
	public static final int NULL_X = 14;
	public static final int NULL_Y = 15;

	////////// MAP OR LIST ////////////

	private static HashMap<Identifier, ButtonEvent> button = new HashMap<>();

	private static Type type;
	
	public static void buttonAttribution(Type type) {
		if(type == Type.GAMEPAD) {		
			button.put(Identifier.Button._0, new ButtonEvent(A));
			button.put(Identifier.Button._1, new ButtonEvent(B));
			button.put(Identifier.Button._2, new ButtonEvent(X));
			button.put(Identifier.Button._3, new ButtonEvent(Y));
			button.put(Identifier.Button._4, new ButtonEvent(L1));
			button.put(Identifier.Button._5, new ButtonEvent(R1));				
			button.put(Identifier.Button._6, new ButtonEvent(SELECT));
			button.put(Identifier.Button._7, new ButtonEvent(START));
			button.put(Identifier.Button._8, new ButtonEvent(LSTICK));
			button.put(Identifier.Button._9, new ButtonEvent(RSTICK));
		}
				
		if(type == Type.STICK) {
			button.put(Identifier.Button._2, new ButtonEvent(A));
			button.put(Identifier.Button._1, new ButtonEvent(B));
			button.put(Identifier.Button._3, new ButtonEvent(X));
			button.put(Identifier.Button._0, new ButtonEvent(Y));
			button.put(Identifier.Button._4, new ButtonEvent(L1));
			button.put(Identifier.Button._5, new ButtonEvent(R1));
			button.put(Identifier.Button._8, new ButtonEvent(SELECT));
			button.put(Identifier.Button._9, new ButtonEvent(START));			
			button.put(Identifier.Button._10, new ButtonEvent(LSTICK));
			button.put(Identifier.Button._11, new ButtonEvent(RSTICK));
		}
		
		ButtonEvent.type = type;		
	}

	public static ButtonEvent getButtonEvent(Type type, Identifier identifier, float value) {
		if(ButtonEvent.type != type)
			buttonAttribution(type);
		
		if (identifier instanceof Identifier.Button)
			return button.get(identifier);

		if (identifier == Identifier.Axis.X) {
			if (value < -0.05f)
				return new ButtonEvent(ButtonEvent.LEFT);
			if (value > 0.05f)
				return new ButtonEvent(ButtonEvent.RIGHT);
			else
				return new ButtonEvent(ButtonEvent.NULL_X);
		}

		if (identifier == Identifier.Axis.Y) {
			if (value < -0.05f)
				return new ButtonEvent(ButtonEvent.UP);
			if (value > 0.05f)
				return new ButtonEvent(ButtonEvent.DOWN);
			else
				return new ButtonEvent(ButtonEvent.NULL_Y);
		}

		return new ButtonEvent(ERROR);
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
