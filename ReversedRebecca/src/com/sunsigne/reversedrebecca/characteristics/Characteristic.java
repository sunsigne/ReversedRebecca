package com.sunsigne.reversedrebecca.characteristics;

public abstract class Characteristic {

	public Characteristic() {
		CharacteristicList.getList().removeObject(this);
		CharacteristicList.getList().addObject(this);
	}
	
	protected abstract Characteristic getInstance();
	
	public abstract void reset();
	
}
