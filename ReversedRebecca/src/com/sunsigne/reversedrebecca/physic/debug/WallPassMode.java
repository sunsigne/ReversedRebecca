package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class WallPassMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new WallPassMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public int getNum() {
		return 2;
	}

	@Override
	public String getName() {
		return "debugmode_wall_pass";
	}

	////////// TICK ////////////

	public static boolean isActive() {
		return debugMode.getState();
	}

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		setPlayerTransluant(g, object, true);
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		setPlayerTransluant(g, object, false);
	}

	private void setPlayerTransluant(Graphics g, Updatable object, boolean transluant) {
		if (getState() == false)
			return;

		if (object instanceof Player == false)
			return;

		Graphics2D g2d = (Graphics2D) g;
		float alpha = transluant ? 0.4f : 1f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

}
