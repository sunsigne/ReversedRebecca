package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class VisibleHitboxMode extends DebugMode {

	////////// PHYSIC LAW ////////////

	private static PhysicLaw physicLaw = new VisibleHitboxMode();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "debug_visible_hitbox_mode";
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

		if (!getState())
			return;

		Graphics2D g2d = (Graphics2D) g;

		collisionDetectorRender(g2d, object);
		collisionReactorRender(g2d, object);
		mouseUserEventRender(g2d, object);
	}

	///// useful /////
	
	private void redHitboxRender(Graphics2D g2d, Rectangle bounds) {
		g2d.setColor(Color.WHITE);
		g2d.draw(bounds);

		g2d.setColor(Color.RED);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		g2d.fill(bounds);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

	///// detector /////
	
	private void collisionDetectorRender(Graphics2D g2d, Updatable object) {
		if (object instanceof CollisionDetector == false)
			return;

		CollisionDetector detectorObject = (CollisionDetector) object;

		g2d.setColor(Color.WHITE);
		g2d.draw(detectorObject.getBounds(DIRECTION.LEFT));
		g2d.draw(detectorObject.getBounds(DIRECTION.RIGHT));
		g2d.draw(detectorObject.getBounds(DIRECTION.UP));
		g2d.draw(detectorObject.getBounds(DIRECTION.DOWN));
	}

	///// reactor /////
	
	private void collisionReactorRender(Graphics2D g2d, Updatable object) {
		if (object instanceof CollisionReactor == false)
			return;

		CollisionReactor reactorObject = (CollisionReactor) object;
		redHitboxRender(g2d, reactorObject.getBounds());
	}

	///// mouse /////
	
	private void mouseUserEventRender(Graphics2D g2d, Updatable object) {
		if (object instanceof CollisionReactor)
			return;
		
		if (object instanceof MouseUserEvent == false)
			return;

		MouseUserEvent mouseObject = (MouseUserEvent) object;
		Rectangle bounds = new Rectangle(mouseObject.getX(), mouseObject.getY(), mouseObject.getWidth(),
				mouseObject.getHeight());
		
		redHitboxRender(g2d, bounds);
	}

	////////// KEYBOARD ////////////

	@Override
	protected int getKeyEvent() {
		return KeyEvent.VK_F3;
	}

}