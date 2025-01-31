package com.sunsigne.reversedrebecca.system.controllers;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadAdapter;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadManager;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePreseting;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class ControllerManager {

	////////// SIGNELTON ////////////

	private ControllerManager() {

	}

	private static ControllerManager instance;

	public static ControllerManager getInstance() {
		if (instance == null)
			instance = new ControllerManager();
		return instance;
	}

	////////// GAMEPAD ////////////

	private boolean usingGamepad;

	public boolean isUsingGamepad() {
		return usingGamepad;
	}

	public void setUsingGamepad(boolean usingGamepad) {
		if (usingGamepad == false)
			GamepadController.pressing.clear();

		if (this.usingGamepad != usingGamepad)
			updateGamepadDiplay(usingGamepad);

		this.usingGamepad = usingGamepad;
	}

	////////// UPDATE ////////////

	private static void updateGamepadDiplay(boolean active) {
		if (active)
			activeGamepadDisplay();
		else
			desactiveGamepadDisplay();
	}

	private static boolean flag;
	
	private static void activeGamepadDisplay() {
		if(flag)
			return;
		
		flag = true;
		displayGamepadWarning();
		Game.getInstance().setCursor(CURSOR_TYPE.NULL.getCursor());
		resetMousePresetings();
	}

	private static void displayGamepadWarning() {
		String lang = Language.getInstance().getLang();
		String title = "WARNING";
		String message = "Controller support is still under development. It is unstable on this version of the game.";
		String path = "textures/other/coming_soon";

		if (lang.equalsIgnoreCase("french")) {
			title = "AVERTISSEMENT";
			message = "Le support manette est encore en développement. Il est instable sur cette version du jeu.";
			path = path.concat("_fr");
		}
		
		BufferedImage image = new ImageTask().loadImage(path);
		ImageIcon icon = new ImageIcon(image);
		new SoundTask().playSound(SOUNDTYPE.ERROR, "error");
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, icon);
	}

	private static void resetMousePresetings() {
		var list = GamepadManager.getList();
		for (GamepadAdapter tempAdapter : list.getList()) {

			if (tempAdapter instanceof GamepadController == false)
				continue;
			var tempEvent = ((GamepadController) tempAdapter).getGamepadEvent();

			if (tempEvent instanceof MousePreseting == false)
				continue;
			var tempPreset = (MousePreseting) tempEvent;

			tempPreset.setPreset(MousePreseting.NULL);
		}
	}

	private static void desactiveGamepadDisplay() {
		new GameCursor().refreshCursor();
	}

}
