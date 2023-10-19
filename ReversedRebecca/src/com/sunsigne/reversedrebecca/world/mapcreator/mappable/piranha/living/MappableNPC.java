package com.sunsigne.reversedrebecca.world.mapcreator.mappable.piranha.living;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.MappableComplexe;

public class MappableNPC implements MappableComplexe {

	private MappableNPC() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNPC();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int red, int green, int blue, int x, int y) {
		String name = "NPC-" + new FormattedString().getNumber(green);
		return new NPC(name, x, y);
	}

	@Override
	public boolean isValidGreen(int green) {
		return 0 < green && green <= 30;
	}

	@Override
	public int[] rgbCode() {
		// green = 1 to 30
		int[] rgb = { 255, -1, 0 };
		return rgb;
	}

}
