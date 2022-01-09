package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.debug.VisibleHitboxObject;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.LAYER;

public class VisibleHitboxMode extends DebugMode {

	private static VisibleHitboxMode debugMode = new VisibleHitboxMode();

	static {
		PhysicList.getList().addObject(debugMode);
		LAYER.DEBUG.addObject(new VisibleHitboxObject(debugMode));
	}

	////////// DEBUG MODE ////////////

	@Override
	public int getIndex() {
		return 2;
	}
	
	@Override
	protected void actionWhenTurnedOn() {

	}

	@Override
	protected void actionWhenTurnedOff() {

	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
	
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {
		if (object == null)
			return;

		if(!getState())
			return;
		
		Graphics2D g2d = (Graphics2D) g;

		collisionDetectorRender(g2d, object);
		collisionReactorRender(g2d, object);
	}

	private void collisionDetectorRender(Graphics2D g2d, Updatable object) {

		CollisionDetector detectorObject = null;

		if (object instanceof CollisionDetector)
			detectorObject = (CollisionDetector) object;
		else
			return;

		g2d.setColor(Color.WHITE);
		g2d.draw(detectorObject.getBounds(DIRECTION.LEFT));
		g2d.draw(detectorObject.getBounds(DIRECTION.RIGHT));
		g2d.draw(detectorObject.getBounds(DIRECTION.UP));
		g2d.draw(detectorObject.getBounds(DIRECTION.DOWN));
	}

	private void collisionReactorRender(Graphics2D g2d, Updatable object) {

		CollisionReactor reactorObject = null;

		if (object instanceof CollisionReactor)
			reactorObject = (CollisionReactor) object;
		else
			return;

		g2d.setColor(Color.WHITE);
		g2d.draw(reactorObject.getBounds());
		
		g2d.setColor(Color.RED);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		g2d.fill(reactorObject.getBounds());
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return KeyEvent.VK_F2;
	}

}