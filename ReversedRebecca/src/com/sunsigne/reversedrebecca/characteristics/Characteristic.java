package com.sunsigne.reversedrebecca.characteristics;

public abstract class Characteristic {

	public Characteristic() {
		CharacteristicList.getList().removeObject(this);
		CharacteristicList.getList().addObject(this);
	}
	
	public abstract void reset();
	
}
