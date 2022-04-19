package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.computer;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.computer.ComputerObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenComputer implements Mappable {

	private MappableGreenComputer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGreenComputer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ComputerObject(LVL.GREEN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 255, 2 };
		return rgb;
	}

}
