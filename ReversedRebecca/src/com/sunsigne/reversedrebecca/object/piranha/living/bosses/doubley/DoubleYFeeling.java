package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling;

public interface DoubleYFeeling extends Feeling {

	////////// DOUBLE Y FEELING ////////////

	public DOUBLE_Y_CONDITION getDoubleYCondition();

	public void setDoubleYCondition(DOUBLE_Y_CONDITION doubleYCondition);

	////////// DOUBLE Y CONDITION ////////////

	public enum DOUBLE_Y_CONDITION {
		GOOD("good"), PUSH_UP("push_up");

		private String name;

		DOUBLE_Y_CONDITION(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
