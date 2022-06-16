package com.sunsigne.reversedrebecca.object.characteristics;

public interface DevDifficulty extends Difficulty {

	// you should NOT use it : the user must decide the Game Difficulty.
	// this exist for test purposes only

	////////// DEV DIFFICULTY ////////////

	public DEV_LVL getDevDifficulty();

	////////// DEV LVL ////////////

	public enum DEV_LVL {
		EASIEST, EASIER, HARDER, HARDEST;

		public boolean isEasy() {
			return this == EASIEST | this == EASIER;
		}

		public boolean isHard() {
			return this == HARDER | this == HARDEST;
		}
	}

}
