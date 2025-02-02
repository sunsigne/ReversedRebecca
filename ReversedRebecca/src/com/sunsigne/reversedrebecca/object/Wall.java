package com.sunsigne.reversedrebecca.object;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class Wall extends GameObject implements TickFree, RenderFree, CollisionReactor {

	public Wall(int x, int y, int width, int height) {
		super(x, y);

		setBounds(width, height);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "WALL";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + goal.getX() + "-" + goal.getY();
	}

	////////// COLOR ////////////

	public enum COLOR {
		BROWN("brown"), BROWN_SUGAR("brown_sugar"), BLUE("blue"), GREEN("green"), WHITE("white");

		private String name;

		COLOR(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.COLLISIONNER;
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return true;
	}

	@Override
	public boolean isBlockingPath() {
		return true;
	}

	private int hitboxW = getWidth();
	private int hitboxH = getHeight();

	public void setBounds(int width, int height) {
		this.hitboxW = getWidth() * width;
		this.hitboxH = getHeight() * height;
	}

	@Override
	public Rectangle getBounds() {
		int x = getX();
		int y = getY();
		int w = hitboxW;
		int h = hitboxH;
		return new Rectangle(x, y, w, h);
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}
