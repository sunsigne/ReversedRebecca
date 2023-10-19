package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.RockPiranhaObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.MappableComplexe;

public class MappableRock implements MappableComplexe {

	private MappableRock() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableRock();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int red, int green, int blue, int x, int y) {
		int x0 = red - 128;
		int y0 = green - 128;
		int type = 255 - blue;

		return new RockPiranhaObject(x, y, x0, y0, type);
	}

	@Override
	public boolean isValidRed(int red) {
		return 120 <= red && red <= 136;
	}

	@Override
	public boolean isValidGreen(int green) {
		return 120 <= green && green <= 136;
	}

	@Override
	public boolean isValidBlue(int blue) {
		return 241 <= blue;
	}

	@Override
	public int[] rgbCode() {
		// red = 120 to 136
		// green = 120 to 136
		// blue = 241 to 255
		int[] rgb = { -1, -1, -1 };
		return rgb;
	}

}
