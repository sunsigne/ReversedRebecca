package com.sunsigne.reversedrebecca.characteristics.drunk;

public class DrunkTask {

	private static int drunk;

	public static int getDrunk() {
		return drunk;
	}

	public void setDrunk(int drunk) {
		DrunkTask.drunk = drunk;
	}

	public void addDrunk() {
		addDrunk(1);
	}

	public void addDrunk(int amount) {
		setDrunk(getDrunk() + amount);
	}

	public void removeDrunk() {
		removeDrunk(1);
	}

	public void removeDrunk(int amount) {
		setDrunk(getDrunk() - amount);
	}

	public boolean isDead() {
		return getDrunk() >= 10;

	}
}
