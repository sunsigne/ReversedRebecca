package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
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

	////////// DEBUG MODE ////////////

	@Override
	public String getName() {
		return "debug_wall_pass_mode";
	}

	@Override
	protected void actionWhenTurnedOn() {
//		PhysicList.getList().removeObject(new CollisionLaw().getPhysicLaw());
	}

	@Override
	protected void actionWhenTurnedOff() {
//		PhysicList.getList().addObject(new CollisionLaw().getPhysicLaw());
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (getState()) {
			if(object instanceof Player)
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

	////////// KEYBOARD ////////////

	@Override
	protected int getKeyEvent() {
		return KeyEvent.VK_F4;
	}

}
