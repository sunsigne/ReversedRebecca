package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.green.vertical;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableGreenVerticalYellowRubble implements Mappable {

	private MappableGreenVerticalYellowRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableGreenVerticalYellowRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.YELLOW, COLOR.GREEN, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 240, 3 };
		return rgb;
	}

}
