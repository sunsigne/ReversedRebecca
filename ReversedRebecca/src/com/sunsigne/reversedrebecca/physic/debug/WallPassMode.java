package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.laws.CollisionLaw;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class WallPassMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new WallPassMode();
	
	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	@Override
	protected void actionWhenTurnedOn() {
		PhysicList.getList().removeObject(new CollisionLaw());
	}

	@Override
	protected void actionWhenTurnedOff() {
		PhysicList.getList().addObject(new CollisionLaw());
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}
	
	////////// TEXTURE ////////////

	private BufferedImage img = new ImageTask().loadImage("textures/debug_wall_pass_mode.png");
	
	@Override
	public BufferedImage getImage() {
		return img;
	}	

	////////// RENDER ////////////

	private boolean isDetectorObject(Updatable object) {
		if (object == null)
			return false;

		if (object instanceof CollisionDetector)
			return true;

		return false;
	}

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {
		
		Graphics2D g2d = (Graphics2D) g;
		float alpha = getState() & isDetectorObject(object) ? 0.4f : 1f;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

	////////// KEYBOARD ////////////

	@Override
	protected int getKeyEvent() {
		return KeyEvent.VK_F3;
	}

}
