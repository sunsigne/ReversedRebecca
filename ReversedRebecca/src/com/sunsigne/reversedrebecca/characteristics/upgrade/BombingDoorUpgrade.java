package com.sunsigne.reversedrebecca.characteristics.upgrade;

public class BombingDoorUpgrade extends UpgradePlayer {

	public BombingDoorUpgrade() {

	}

	////////// TOOL ////////////

	private static UpgradePlayer instance = new BombingDoorUpgrade();

	@Override
	protected UpgradePlayer getInstance() {
		return instance;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "BombingDoor";
	}

}
