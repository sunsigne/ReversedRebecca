package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.computer.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.computer.ComputerObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableEasiestComputer implements Mappable {

	private MappableEasiestComputer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableEasiestComputer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ComputerObject(DEV_LVL.EASIEST, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 3, 255, 7 };
		return rgb;
	}

}
