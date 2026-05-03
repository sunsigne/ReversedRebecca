package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling;

public interface DoubleYFeeling extends Feeling {

	////////// DOUBLE Y FEELING ////////////

	public DOUBLE_Y_CONDITION getDoubleYCondition();

	public void setDoubleYCondition(DOUBLE_Y_CONDITION doubleYCondition);

	////////// DOUBLE Y CONDITION ////////////

	public enum DOUBLE_Y_CONDITION {
		GOOD("good"), TIRED("tired"), PUSH_UP("push_up"), PUSH_UP_ONE_HAND("push_up_one_hand"), THROWING("throwing"),
		PUNCHING("punching"), TORNADO("tornado"), UPPERCUT("uppercut"), DBZ_TP("dbz_tp"), FLEX_1("flex_1"),
		FLEX_2("flex_2"), FLEX_3("flex_3"), FLEX_4("flex_4"), BALL("ball");

		private String name;

		DOUBLE_Y_CONDITION(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
