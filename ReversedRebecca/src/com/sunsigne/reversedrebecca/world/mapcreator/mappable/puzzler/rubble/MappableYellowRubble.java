package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableYellowRubble implements Mappable {

	private MappableYellowRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableYellowRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.YELLOW, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 255, 3 };
		return rgb;
	}
	
}
