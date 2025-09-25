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
	public int getNum() {
		return 1;
	}
	
	@Override
	public String getName() {
		return "BombingDoor";
	}

}
