package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;

public interface Mappable {

	////////// MAPPABLE ////////////
	
	GameObject createObject(int x, int y);
		
	int[] rgbCode();
	
	public default int getRedCode() {
		return rgbCode()[0];
	}
	
	public default int getGreenCode() {
		return rgbCode()[1];
	}
	
	public default int getBlueCode() {
		return rgbCode()[2];
	}
}
