package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.green.vertical.dev;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject.DEV_LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenVerticalEasiestRubble implements Mappable {

	private MappableGreenVerticalEasiestRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGreenVerticalEasiestRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(DEV_LVL.EASIEST, COLOR.GREEN, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 240, 7 };
		return rgb;
	}

}
