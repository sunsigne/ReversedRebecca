package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.computer;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.computer.NullComputerObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableNullComputer implements Mappable {

	private MappableNullComputer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableNullComputer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new NullComputerObject(x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 3, 255, 0 };
		return rgb;
	}

}
