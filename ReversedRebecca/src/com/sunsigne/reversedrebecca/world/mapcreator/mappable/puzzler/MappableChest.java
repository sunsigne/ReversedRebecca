package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.chest.ChestObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.FreeChestObject;
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

		boolean little = blue < 254;
		LVL difficulty = LVL.NULL;

		while (green > 128) {
			green--;
			difficulty = difficulty.getNext();
		}

		if (red == 0)
			return new NullChestObject(x, y, little);

		else if (blue % 2 == 0)
			return new FreeChestObject(red, x, y, little);
		else
			return new ChestObject(difficulty, red, x, y, little);
	}

	@Override
	public boolean isValidRed(int red) {
		return red < 20;
	}

	@Override
	public boolean isValidGreen(int green) {
		return 128 <= green && green <= 134;
	}

	@Override
	public boolean isValidBlue(int blue) {
		return 251 <= blue;
	}

	@Override
	public int[] rgbCode() {
		// red = 0 to 9
		// green = 128 to 134
		// bkue = 251 to 255
		int[] rgb = { -1, 128, 256 };
		return rgb;
	}

}
