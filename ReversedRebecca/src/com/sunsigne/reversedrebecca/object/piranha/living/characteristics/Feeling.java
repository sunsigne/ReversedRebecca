package com.sunsigne.reversedrebecca.object.piranha.living.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.Stunnable;

public interface Feeling extends Stunnable {

	////////// STUNNABLE ////////////

	default boolean isStunned() {
		switch (getCondition()) {
		case GOOD:
		case GLASS:
		case CUP:
		case SICK:
		case KO_UPSIDEDOWN_NOT_STUNNED:
			return false;
		case BATH:
		case THINK:
		case BED:
		case CRY:
		case RELAX:
		case SIT:
		case KO:
		case KO_UPSIDEDOWN:
			return true;
		}
		return false;
	}

	////////// FEELING ////////////

	public CONDITION getCondition();

	public void setCondition(CONDITION condition);

	////////// CONDITION ////////////

	public enum CONDITION {
		GOOD("good"), GLASS("glass"), CUP("cup"), SICK("sick"), BATH("bath"), THINK("think"), BED("bed"), CRY("cry"), RELAX("relax"), SIT("sit"), KO("ko"), KO_UPSIDEDOWN("ko_upsidedown"), KO_UPSIDEDOWN_NOT_STUNNED("ko_upsidedown_not_stunned");

		private String name;

		CONDITION(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

}
