package com.sunsigne.reversedrebecca.object.characteristics;

public interface SpeedVariator extends Velocity {

	////////// SPEED VARIATOR ////////////

	SPEEDNESS getSpeedness();

	void setSpeedness(SPEEDNESS speedness);

	default int getSpeed() {
		switch (getSpeedness()) {
		case SLOW:
			return getSize() / 32;
		case NORMAL:
			return getSize() / 16;
		case FAST:
			return getSize() / 8;
		case PLAYER_SPEED:
			return getSize() / 12;
		case SWIFT:
			return getSize() / 4;
		}
		return getSize() / 16;
	}

	////////// SPEED ////////////

	// it looks random, but each speed must be a multiple of Size.M
	// not to corrupt pathfinding (except for player outside cutscene)
	public enum SPEEDNESS {
		SLOW("slow"), NORMAL("move"), FAST("fast"), PLAYER_SPEED("move_player"), SWIFT("swift");

		private String name;

		SPEEDNESS(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
