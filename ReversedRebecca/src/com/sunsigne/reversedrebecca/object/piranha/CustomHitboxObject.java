package com.sunsigne.reversedrebecca.object.piranha;

import java.awt.Rectangle;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class CustomHitboxObject extends GameObject implements TickFree, RenderFree, CollisionReactor {

	public CustomHitboxObject(int x, int y, int hitboxX, int hitboxY, int type) {
		super(x, y);

		carvingHitbox(hitboxX, hitboxY, type);
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.COLLISIONNER;
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return true;
	}

	private void carvingHitbox(int hitboxX, int hitboxY, int type) {
		int[] rect = getRectFromType(type);
		setBounds(hitboxX + rect[0], hitboxY + rect[1], rect[2], rect[3]);
	}

	private int[] getRectFromType(int type) {
		switch (type) {
		case 1:
			return new int[] { 0, -2, 8, 11 }; // pyramidal rock
		case 2:
			return new int[] { 0, -2, 10, 10 }; // small rock
		case 3:
			return new int[] { 0, -2, 13, 11 }; // flat rock
		case 4:
			return new int[] { 0, -2, 12, 16 }; // big rock
		case 5:
			return new int[] { 0, 0, 16, 17 }; // horizontal couch
		case 6:
			return new int[] { 0, 0, 15, 10 }; // vertical couch
		case 7:
			return new int[] { 0, 0, 16, 9 }; // horizontal desk
		case 8:
			return new int[] { 6, 1, 9, 13 }; // big horizontal desk
		default:
			return new int[] { 0, 0, 16, 16 };
		}
	}

	private int hitboxX, hitboxY;
	private int hitboxW = getWidth();
	private int hitboxH = getHeight();

	// width and height should be between 0 and 16
	public void setBounds(int x, int y, int width, int height) {
		int pixel = 16;
		int ratio = Size.M / pixel;
		this.hitboxX = x * ratio;
		this.hitboxY = y * ratio;
		this.hitboxW = width * ratio;
		this.hitboxH = height * ratio;
	}

	@Override
	public Rectangle getBounds() {
		int x = getX() + hitboxX;
		int y = getY() + hitboxY;
		int w = hitboxW;
		int h = hitboxH;
		return new Rectangle(x, y, w, h);
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}
