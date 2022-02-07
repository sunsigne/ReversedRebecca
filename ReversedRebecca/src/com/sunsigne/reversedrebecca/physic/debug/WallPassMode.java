package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.laws.CollisionLaw;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class WallPassMode extends DebugMode {

	////////// PHYSIC LAW ////////////

	private static PhysicLaw physicLaw = new WallPassMode();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "wall_pass_mode";
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (getState()) {
			if (object instanceof Player)
				PhysicList.getList().removeObject(new CollisionLaw().getPhysicLaw());
			else
				PhysicList.getList().addObject(new CollisionLaw().getPhysicLaw());
		}
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

		Graphics2D g2d = (Graphics2D) g;
		float alpha = getState() & object instanceof Player ? 0.4f : 1f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
