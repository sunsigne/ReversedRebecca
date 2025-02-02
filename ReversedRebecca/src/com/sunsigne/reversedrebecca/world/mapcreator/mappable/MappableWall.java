package com.sunsigne.reversedrebecca.world.mapcreator.mappable;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;

public class MappableWall implements MappableComplexe {

	private MappableWall() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableWall();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int red, int green, int blue, int x, int y) {
		int x0 = 256 - green;
		int y0 = 256 - blue;

		return new Wall(x, y, x0, y0);
	}

	@Override
	public boolean isValidGreen(int green) {
		return 155 <= green;
	}

	@Override
	public boolean isValidBlue(int blue) {
		return 155 <= blue;
	}

	@Override
	public int[] rgbCode() {
		// green = 155 to 255
		// blue = 155 to 255
		int[] rgb = { 255, -1, -1 };
		return rgb;
	}

}
