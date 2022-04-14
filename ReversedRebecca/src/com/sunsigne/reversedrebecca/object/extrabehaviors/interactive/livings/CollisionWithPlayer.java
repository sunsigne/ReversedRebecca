package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings;

public interface CollisionWithPlayer {

	////////// DIFFICULTY ////////////

	public COLLISIONTYPE getCollisionType();

	public void setCollisionType(COLLISIONTYPE collisionType);

	////////// LVL ////////////

	public enum COLLISIONTYPE {
		PUSH("push"), PUSH_HURT("push_hurt"), AROUND("around"), STOP("stop");

		private String name;

		COLLISIONTYPE(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
