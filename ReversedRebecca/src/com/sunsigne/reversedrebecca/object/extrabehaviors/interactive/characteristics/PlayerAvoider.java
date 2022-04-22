package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;
import com.sunsigne.reversedrebecca.object.piranha.characteristics.Pusher;

public interface PlayerAvoider extends Pusher, PathSearcher {

	////////// PLAYER AVOIDER ////////////
	
	AVOIDERTYPE getPlayerAvoiderType();

	void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType);

	////////// AVOIDER TYPE ////////////

	public enum AVOIDERTYPE {
		AROUND("around"), STOP("stop"), PUSH("push"), PUSH_HURT("push_hurt"), PUSH_LEFT("push_left"),
		PUSH_RIGHT("push_right"), PUSH_UP("push_up"), PUSH_DOWN("push_down");

		private String name;

		AVOIDERTYPE(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
