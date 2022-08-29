package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble.blue.horizontal;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBlueHorizontalYellowRubble implements Mappable {

	private MappableBlueHorizontalYellowRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBlueHorizontalYellowRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.YELLOW, true, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 255, 3 };
		return rgb;
	}
	
}
