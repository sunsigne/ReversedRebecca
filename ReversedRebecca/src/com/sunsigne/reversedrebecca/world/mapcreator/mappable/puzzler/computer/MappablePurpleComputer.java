package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.computer;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.computer.ComputerObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappablePurpleComputer implements Mappable {

	private MappablePurpleComputer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappablePurpleComputer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ComputerObject(LVL.PURPLE, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 255, 6 };
		return rgb;
	}

}
