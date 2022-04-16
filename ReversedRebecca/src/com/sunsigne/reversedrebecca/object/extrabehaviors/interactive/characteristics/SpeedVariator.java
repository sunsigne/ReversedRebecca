package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.PathSearcher;

public interface SpeedVariator extends PathSearcher {

	////////// SPEED VARIATOR ////////////

	SPEEDTYPE getSpeedType();

	void setSpeedType(SPEEDTYPE speedType);

	default int getSpeed() {
		return getSpeedType().getSpeed();
	}

	////////// SPEED ////////////

	// it looks random, but each speed must be a multiple of Size.M
	// not to corrupt pathfinding (except for player outside cutscene)
	public enum SPEEDTYPE {
		SLOW("slow", 3), NORMAL("move", 6), FAST("fast", 16), PLAYER_SPEED("move_player", 10);

		private String name;
		private int speed;

		SPEEDTYPE(String name, int speed) {
			this.name = name;
			this.speed = speed;
		}

		public String getName() {
			return name;
		}

		public int getSpeed() {
			return speed;
		}
	}

}
