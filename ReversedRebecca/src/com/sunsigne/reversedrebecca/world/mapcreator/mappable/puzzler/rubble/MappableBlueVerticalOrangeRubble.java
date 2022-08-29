package com.sunsigne.reversedrebecca.world.mapcreator.mappable.puzzler.rubble;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.world.mapcreator.MapCreator;
import com.sunsigne.reversedrebecca.world.mapcreator.mappable.Mappable;

public class MappableBlueVerticalOrangeRubble implements Mappable {

	private MappableBlueVerticalOrangeRubble() {
		new MapCreator().getList().addObject(this);
	}

	private static Mappable mappable = new MappableBlueVerticalOrangeRubble();

	@Override
	public Mappable getMappable() {
		return mappable;
	}

	////////// MAPPABLE ////////////

	@Override
	public GameObject createObject(int x, int y) {
		return new RubbleObject(LVL.ORANGE, false, x, y);
	}

	@Override
	public int[] rgbCode() {
		int[] rgb = { 2, 250, 4 };
		return rgb;
	}
	
}
