package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics;

public interface PlayerAvoider {

	public AVOIDERTYPE getPlayerAvoiderType();

	public void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType);

	public default boolean isPlayerBlockingPath() {
		switch (getPlayerAvoiderType()) {
		case AROUND:
			return true;
		default:
			return false;
		}
	}

	////////// AVOIDER TYPE ////////////

	public enum AVOIDERTYPE {
		AROUND("around"), STOP("stop"), PUSH("push"), PUSH_HURT("push_hurt"), PUSH_LEFT("push_left"),
		PUSH_RIGHT("push_right"), PUSH_UP("push_up"), PUSH_DOWN("push_downp");

		private String name;

		AVOIDERTYPE(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
