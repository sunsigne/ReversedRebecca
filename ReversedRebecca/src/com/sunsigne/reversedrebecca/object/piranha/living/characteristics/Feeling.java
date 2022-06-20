package com.sunsigne.reversedrebecca.object.piranha.living.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.Stunnable;

public interface Feeling extends Stunnable {

	////////// STUNNABLE ////////////

	default boolean isStunned() {
		switch (getCondition()) {
		case GOOD:
		case GLASS:
		case SICK:
			return false;
		case BATH:
		case BED:
		case SIT:
		case KO:
			return true;
		}
		return false;
	}

	////////// FEELING ////////////

	public CONDITION getCondition();

	public void setCondition(CONDITION condition);

	////////// CONDITION ////////////

	public enum CONDITION {
		GOOD("good"), GLASS("glass"), SICK("sick"), BATH("bath"), BED("bed"), SIT("sit"), KO("ko");

		private String name;

		CONDITION(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
