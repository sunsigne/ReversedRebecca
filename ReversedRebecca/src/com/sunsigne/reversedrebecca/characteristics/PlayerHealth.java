package com.sunsigne.reversedrebecca.characteristics;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class PlayerHealth extends Characteristic {

	private String file = "userdata/characteristics.csv";

	////////// SIGNELTON ////////////

	private PlayerHealth() {
		super();
		reset();
	}

	private static PlayerHealth instance;

	public static PlayerHealth getInstance() {
		if (instance == null)
			instance = new PlayerHealth();
		return instance;
	}

	////////// CHARACTERISTICS ////////////

	@Override
	public void reset() {
		maxhp = Integer.parseInt(new FileTask().read("MaxHp", file));
		hp = maxhp;
	}

	////////// INVULNERABILITY ////////////

	private boolean invulnerable;

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	////////// MAX HP ////////////

	private int maxhp;

	public int getMaxHp() {
		return maxhp;
	}

	public void setMaxHp(int maxhp) {
		this.maxhp = maxhp;
	}

	public boolean isFullHp() {
		return hp == maxhp;
	}

	public void setFullHp() {
		hp = maxhp;
	}

	////////// HP ////////////

	private int hp;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void addHp() {
		addHp(1);
	}

	public void addHp(int amount) {
		if (hp + amount > maxhp)
			setFullHp();
		else
			hp = hp + amount;
	}

	public void removeHp() {
		if (!isInvulnerable())
			removeHp(1);
	}

	public void removeHp(int amount) {
		if (!isInvulnerable())
			hp = hp - amount;
	}

	public boolean isDead() {
		return hp <= 0;
	}

}
