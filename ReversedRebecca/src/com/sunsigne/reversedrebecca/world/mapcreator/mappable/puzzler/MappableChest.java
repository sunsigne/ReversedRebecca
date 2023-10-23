package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.ChestObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.NullChestObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.MappableComplexe;

public class MappableChest implements MappableComplexe {

	private MappableChest() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableChest();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int red, int green, int blue, int x, int y) {
		if (red == 0)
			return new NullChestObject(x, y);
		else
			return new ChestObject(red, x, y);
	}

	@Override
	public boolean isValidRed(int red) {
		return red < 10;
	}

	@Override
	public int[] rgbCode() {
		// red = 0 to 9
		int[] rgb = { -1, 128, 255 };
		return rgb;
	}

}
