package com.sunsigne.reversedrebecca.system.controllers.keyboard.keys;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;

public class ActionThreeKey implements Key {

	////////// KEY ////////////

	private static int key;

	public static int getKey() {
		if (key == 0)
			key = new ActionThreeKey().loadKey();
		return key;
	}

	@Override
	public void refreshKey() {
		key = 0;
	}

	@Override
	public String getValueToRead() {
		return "Action3";
	}

	////////// GAMEPAD ////////////

	private static BufferedImage gamepadButton;

	public static BufferedImage getGamepadButton() {
		if (gamepadButton == null)
			gamepadButton = new ImageTask().loadImage("textures/menu/button_" + "y");
		return gamepadButton;
	}

	public static int getGamepadKey() {
		return ButtonEvent.Y;
	}

}