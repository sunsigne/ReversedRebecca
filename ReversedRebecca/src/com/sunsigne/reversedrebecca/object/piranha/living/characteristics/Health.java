package com.sunsigne.reversedrebecca.object.piranha.living.characteristics;

import com.sunsigne.reversedrebecca.object.characteristics.LayerSendable;
import com.sunsigne.reversedrebecca.pattern.GameTimer;

public interface Health extends Feeling, LayerSendable {

	////////// INVULNERABILITY ////////////

	boolean isInvulnerable();

	void setInvulnerable(boolean invulnerable);

	boolean isRecovering();

	void setRecovering(boolean recovering);

	////////// MAX HP ////////////

	int getMaxHp();

	void setMaxHp(int maxhp);

	default boolean isFullHp() {
		return getHp() == getMaxHp();
	}

	default void setFullHp() {
		setHp(getMaxHp());
	}

	////////// HP ////////////

	int getHp();

	void setHp(int hp);

	default void addHp() {
		addHp(1);
	}

	default void addHp(int amount) {
		if (getHp() + amount > getMaxHp())
			setFullHp();
		else
			setHp(getHp() + amount);
	}

	default void removeHp() {
		removeHp(1);
	}

	default void removeHp(int amount) {
		if (isInvulnerable() | isRecovering())
			return;

		setHp(getHp() - amount);
		setRecovering(true);
		new GameTimer(30, () -> setRecovering(false));
	}

	////////// DEATH ////////////
	
	default boolean isDead() {
		return getHp() <= 0;
	}
	
	boolean isRegisteredAsDead();
	
	void registeredAsDead(boolean registeredAsDead);

}
