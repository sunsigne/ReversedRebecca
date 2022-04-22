package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.piranha.living.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.laws.motion.CollisionLaw;
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
	public String getName() {
		return "debugmode_wall_pass";
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (getState()) {
			if (object instanceof PiranhaPlayer)
				PhysicList.getList().removeObject(new CollisionLaw());
			else
				// Yes, this does NOT add the law to the right place, which
				// could cause pathfinding and camera issues.
				// As it's a dev tool, it's not that important anyway.
				PhysicList.getList().addObject(new CollisionLaw());
		}
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

		Graphics2D g2d = (Graphics2D) g;
		float alpha = getState() & object instanceof PiranhaPlayer ? 0.4f : 1f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
