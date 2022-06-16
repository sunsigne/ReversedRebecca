package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.computer.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.computer.ComputerObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableHardestComputer implements Mappable {

	private MappableHardestComputer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableHardestComputer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ComputerObject(DEV_LVL.HARDEST, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 3, 255, 10 };
		return rgb;
	}

}
