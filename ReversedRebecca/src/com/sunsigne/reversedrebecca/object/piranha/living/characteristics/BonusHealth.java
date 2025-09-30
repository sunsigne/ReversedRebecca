package com.sunsigne.reversedrebecca.object.piranha.living.characteristics;

public interface BonusHealth extends Health {

	////////// HP ////////////

	@Override
	default void removeHp(int amount) {
		if (isInvulnerable() | isRecovering())
			return;

		if (amount <= getBonusHp()) {
			setBonusHp(getBonusHp() - amount);
			Health.super.removeHp(0);
			return;
		}

		if (amount > getBonusHp()) {
			int remainder = amount - getBonusHp();
			setBonusHp(0);
			Health.super.removeHp(remainder);
			return;
		}
	}

	////////// BONUS HP ////////////

	int getBonusHp();

	void setBonusHp(int bonusHp);

	default void addBonusHp() {
		addBonusHp(1);
	}

	default void addBonusHp(int amount) {
		setBonusHp(getBonusHp() + amount);
	}

}
