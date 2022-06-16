package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.computer.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.computer.ComputerObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableEasierComputer implements Mappable {

	private MappableEasierComputer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableEasierComputer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ComputerObject(DEV_LVL.EASIER, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 3, 255, 8 };
		return rgb;
	}

}
