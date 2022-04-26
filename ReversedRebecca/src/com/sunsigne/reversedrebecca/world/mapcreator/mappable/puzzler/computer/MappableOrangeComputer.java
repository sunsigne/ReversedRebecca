package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.computer;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.computer.ComputerObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableOrangeComputer implements Mappable {

	private MappableOrangeComputer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableOrangeComputer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ComputerObject(LVL.ORANGE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 3, 255, 4 };
		return rgb;
	}

}
