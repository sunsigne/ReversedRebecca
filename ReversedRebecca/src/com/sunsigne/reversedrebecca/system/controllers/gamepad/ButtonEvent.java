package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import java.util.HashMap;

import net.java.games.input.Component.Identifier;

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

	////////// MAP OR LIST ////////////

	private static HashMap<Identifier, ButtonEvent> button = new HashMap<>();

	public static void buttonAttribution() {
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

	public static ButtonEvent getButtonEvent(Identifier identifier, float value) {
		if (identifier instanceof Identifier.Button)
			return button.get(identifier);

		if (identifier == Identifier.Axis.X) {
			if (value < 0)
				return new ButtonEvent(ButtonEvent.LEFT);
			else
				return new ButtonEvent(ButtonEvent.RIGHT);
		}

		if (identifier == Identifier.Axis.Y) {
			if (value < 0)
				return new ButtonEvent(ButtonEvent.UP);
			else
				return new ButtonEvent(ButtonEvent.DOWN);
		}

		if (identifier == Identifier.Axis.POV) {
			if (value > 0.20f && value < 0.30f)
				return new ButtonEvent(ButtonEvent.UP);
			if (value > 0.45f && value < 0.55f)
				return new ButtonEvent(ButtonEvent.RIGHT);
			if (value > 0.70f && value < 0.80f)
				return new ButtonEvent(ButtonEvent.DOWN);
			else
				return new ButtonEvent(ButtonEvent.LEFT);
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
