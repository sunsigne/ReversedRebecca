package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings;

public interface PlayerAvoider {

	public AVOIDERTYPE getPlayerAvoiderType();

	public void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType);

	public default boolean isPlayerBlockingPath() {
		switch (getPlayerAvoiderType()) {
		case AROUND:
			return true;
		case PUSH:
			return false;
		case PUSH_HURT:
			return false;
		case STOP:
			return false;
		}
		return true;
	}

	////////// AVOIDER TYPE ////////////

	public enum AVOIDERTYPE {
		AROUND("around"), PUSH("push"), PUSH_HURT("push_hurt"), STOP("stop");

		private String name;

		AVOIDERTYPE(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
