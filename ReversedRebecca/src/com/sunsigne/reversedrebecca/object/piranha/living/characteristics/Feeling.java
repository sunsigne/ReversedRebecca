package com.sunsigne.reversedrebecca.object.piranha.living.characteristics;

import com.sunsigne.reversedrebecca.object.piranha.characteristics.Stunnable;

public interface Feeling extends Stunnable {

	////////// STUNNABLE ////////////

	default boolean isStunned() {
		return getCondition() == CONDITION.KO;
	}

	////////// FEELING ////////////

	public CONDITION getCondition();

	public void setCondition(CONDITION condition);

	////////// CONDITION ////////////

	public enum CONDITION {
		GOOD("good"), SICK("sick"), KO("ko");

		private String name;

		CONDITION(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}