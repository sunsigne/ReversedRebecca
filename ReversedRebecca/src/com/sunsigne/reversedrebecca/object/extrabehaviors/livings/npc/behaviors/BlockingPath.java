package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.CollisionBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;

public class BlockingPath implements CollisionBehavior {

	public BlockingPath(NPC npc) {
		this.npc = npc;
	}

	////////// BEHAVIOR ////////////

	private NPC npc;

	@Override
	public NPC getExtraBehaviorsObject() {
		return npc;
	}

	////////// POSITION ////////////

	@Override
	public int getX() {
		return getExtraBehaviorsObject().getX();
	}

	@Override
	public int getY() {
		return getExtraBehaviorsObject().getY();
	}

	@Override
	public void setX(int x) {
		getExtraBehaviorsObject().setX(x);
	}

	@Override
	public void setY(int y) {
		getExtraBehaviorsObject().setY(y);
	}

	////////// SIZE ////////////

	@Override
	public int getWidth() {
		return getExtraBehaviorsObject().getWidth();
	}

	@Override
	public int getHeight() {
		return getExtraBehaviorsObject().getHeight();
	}
	
	////////// COLLISION ////////////
	
	@Override
	public boolean isBlockingSight() {
		return getExtraBehaviorsObject().isBlockingSight();
	}
	
	@Override
	public boolean isBlockingPath() {
		return getExtraBehaviorsObject().isBlockingPath();
	}
	
	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		blockPath(detectorObject);
	}

}