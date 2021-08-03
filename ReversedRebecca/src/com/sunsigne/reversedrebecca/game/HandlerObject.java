package com.sunsigne.reversedrebecca.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.main.IRender;
import com.sunsigne.reversedrebecca.system.main.ITick;
import com.sunsigne.reversedrebecca.util.AnnotationBank.Singleton;

@Singleton
public class HandlerObject implements ITick, IRender {

	////////// SIGNELTON ////////////

	private HandlerObject() {
		startTick();
		startRender();
	}

	private static HandlerObject instance = null;

	public static HandlerObject getInstance() {
		if (instance == null)
			instance = new HandlerObject();
		return instance;
	}

	////////// MAP OR LIST ////////////

	private LinkedList<GameObject> handler_object_list = new LinkedList<>();

	protected void addObject(GameObject object) {
		if (object == null)
			return;

		handler_object_list.add(object);
	}

	protected void removeObject(GameObject object) {
		if (object == null)
			return;

		handler_object_list.remove(object);
	}

//	public void clear() {
//		handler_object_list.clear();
//	}

	public GameObject getObjectAtPos(int x, int y) {
		for (GameObject tempObject : handler_object_list) {
			if (tempObject.getX() == x && tempObject.getY() == y) {
				return tempObject;
			}
		}
		return null;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		for (GameObject tempObject : handler_object_list) {
			tempObject.tick();
			velocity(tempObject);
		}
	}

	private void velocity(GameObject tempObject) {
		tempObject.setX(tempObject.getX() + tempObject.getVelX());
		tempObject.setY(tempObject.getY() + tempObject.getVelY());
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		for (GameObject tempObject : handler_object_list)
			tempObject.render(g);

		if (Conductor.getDebugMode().getHitboxMode().getState()) {
			for (GameObject tempObject : handler_object_list)
				drawHitbox(tempObject, g);
		}
	}

	private void drawHitbox(GameObject tempObject, Graphics g) {
		if (tempObject.getBounds() == null)
			return;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.draw(tempObject.getBounds());
	}

}
