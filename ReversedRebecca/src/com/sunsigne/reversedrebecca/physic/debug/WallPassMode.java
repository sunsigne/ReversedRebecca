package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CollisionLaw;
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

	@Override
	public void tick(Updatable object) {
		if (getState()) {
			if (object instanceof Player)
				// For a weird reason, this tends to "pause" the game, but
				// as it's a dev tool, it's not that important anyway.
				PhysicList.getList().removeObject(new CollisionLaw());
			else
				// Yes, this does NOT add the law to the right place, which
				// could cause pathfinding and camera issues.
				// As it's still a dev tool, it's still not important.
				PhysicList.getList().addObject(new CollisionLaw());
		}
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
