package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.InteractiveObject;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.MappableComplexe;

public class MappableInteractive implements MappableComplexe {

	private MappableInteractive() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableInteractive();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int red, int green, int blue, int x, int y) {
		String name = "OBJ-" + new FormattedString().getNumber(red);
		return new InteractiveObject(name, x, y);
	}

	@Override
	public boolean isValidRed(int red) {
		return red <= 100;
	}

	@Override
	public int[] rgbCode() {
		// red = 1 to 100
		int[] rgb = { -1, 255, 255 };
		return rgb;
	}

}
