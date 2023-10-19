package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;

public interface MappableComplexe extends Mappable {

	////////// MAPPABLE ////////////

	@Override
	public default GameObject createObject(int x, int y) {
		return null;
	}

	public GameObject createObject(int red, int green, int blue, int x, int y);

	public default boolean isValidRGB(int red, int green, int blue) {
		return isValidRed(red) && isValidGreen(green) && isValidBlue(blue);
	}

	public default boolean isValidRed(int red) {
		return getRedCode() == red;
	}

	public default boolean isValidGreen(int green) {
		return getGreenCode() == green;
	}

	public default boolean isValidBlue(int blue) {
		return getBlueCode() == blue;
	}

}
