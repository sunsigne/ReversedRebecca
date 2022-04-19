package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.computer;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.computer.ComputerObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableCyanComputer implements Mappable {

	private MappableCyanComputer() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableCyanComputer();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new ComputerObject(LVL.CYAN, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 255, 1 };
		return rgb;
	}

}
