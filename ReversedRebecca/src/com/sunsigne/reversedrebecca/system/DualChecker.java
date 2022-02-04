package com.sunsigne.reversedrebecca.system;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class DualChecker implements Updatable {

	protected DualChecker() {
		LAYER.DEBUG.addObject(this);
		Game.getInstance().forceLoop();
	}

	private String file = "userdata/dualcheck.csv";

	////////// TICK ////////////

	@Override
	public void tick() {
		verificationProcess();
		updateFile();
	}

	private boolean start;

	private void verificationProcess() {
		if (start)
			return;

		// create a verification file
		new FileTask().write(file, "verification");

		// wait more than one tick
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// close the game if different
		if (!new FileTask().read(file).contains("verification")) {
			stopApp();
		}

		start = true;
	}

	private void updateFile() {
		new FileTask().write(file, "verified");
	}

	private void stopApp() {
//		new SoundTask().playSound(SoundBank.ERROR);
		JOptionPane.showMessageDialog(null, "The game is already running.");
		new Conductor().stopApp();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

}
